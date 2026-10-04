package open.cn.awg.pro.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.Assert.*;

public class NativeHttpTest {
    private ServerSocket server;
    private Thread serverThread;
    private volatile boolean serverRunning;
    private String base;
    private final AtomicReference<SeenRequest> seen = new AtomicReference<>();

    @Before public void startLocalFixture() throws Exception {
        NativeHttp.clearCookies();
        server = new ServerSocket(0, 1, InetAddress.getByName("127.0.0.1"));
        serverRunning = true;
        serverThread = new Thread(this::serve, "native-http-test-server");
        serverThread.setDaemon(true);
        serverThread.start();
        base = "http://127.0.0.1:" + server.getLocalPort();
    }

    @After public void stopLocalFixture() throws Exception {
        serverRunning = false;
        if (server != null) server.close();
        if (serverThread != null) serverThread.join(1000);
        NativeHttp.clearCookies();
    }

    @Test public void formAndJsonRequestsPreserveWireFormat() throws Exception {
        NativeHttp.postForm(base + "/capture", new String[]{"text=中文 +&", "value=a=b"}, "UTF-8",
                null, false, Map.of(), 2000, 2000, null);
        assertEquals("POST", seen.get().method);
        assertEquals("text=%E4%B8%AD%E6%96%87%20%2B%26&value=a%3Db", seen.get().body);
        NativeHttp.postJson(base + "/capture", "{\"消息\":\"内容🙂\"}", "UTF-8",
                null, false, Map.of(), 2000, 2000, null);
        assertEquals("{\"消息\":\"内容🙂\"}" + System.lineSeparator(), seen.get().body);
        assertEquals("UTF-8", seen.get().charsetHeader);
    }

    @Test public void cookieStorageCanBeDisabledAndEnabled() {
        get("/set", false);
        get("/capture", true);
        assertNull(seen.get().cookie);
        get("/set", true);
        get("/capture", true);
        assertTrue(seen.get().cookie.contains("token=fixture"));
    }

    @Test public void redirectsUseGetAndStoredCookies() {
        NativeHttp.postForm(base + "/redirect", new String[]{"text=fixture"}, "UTF-8", "manual=original",
                false, Map.of("X-Fixture", "original"), 2000, 2000, null);
        assertEquals("GET", seen.get().method);
        assertEquals("", seen.get().body);
        assertNull(seen.get().customHeader);
        assertTrue(seen.get().cookie.contains("token=fixture"));
        assertFalse(seen.get().cookie.contains("manual=original"));
    }

    @Test public void non200ResponseRetainsStatusButLegacyBodyIsNull() {
        NativeHttp.Response response = get("/unavailable", false);
        assertEquals(503, response.statusCode);
        assertNull(response.bodyForHttp200());
        assertEquals("中文响应🙂", response.body);
    }

    private NativeHttp.Response get(String path, boolean cookies) {
        return NativeHttp.get(base + path, "UTF-8", null, cookies, Collections.emptyMap(), 2000, 2000, null);
    }

    private void serve() {
        while (serverRunning) {
            try (Socket socket = server.accept()) {
                socket.setSoTimeout(5000);
                SeenRequest request = readRequest(socket.getInputStream());
                if (request != null) {
                    seen.set(request);
                    writeResponse(socket.getOutputStream(), request.path);
                }
            } catch (IOException exception) {
                if (serverRunning) throw new RuntimeException(exception);
            }
        }
    }

    private static SeenRequest readRequest(InputStream input) throws IOException {
        String requestLine = readLine(input);
        if (requestLine == null || requestLine.isEmpty()) return null;
        String[] requestParts = requestLine.split(" ", 3);
        if (requestParts.length < 2) throw new IOException("Malformed HTTP request line");

        SeenRequest request = new SeenRequest();
        request.method = requestParts[0];
        String target = requestParts[1];
        int query = target.indexOf('?');
        request.path = query < 0 ? target : target.substring(0, query);

        int contentLength = 0;
        String line;
        while ((line = readLine(input)) != null && !line.isEmpty()) {
            int separator = line.indexOf(':');
            if (separator < 0) continue;
            String name = line.substring(0, separator).trim();
            String value = line.substring(separator + 1).trim();
            if (name.equalsIgnoreCase("Content-Length")) {
                contentLength = Integer.parseInt(value);
            } else if (name.equalsIgnoreCase("Cookie")) {
                request.cookie = value;
            } else if (name.equalsIgnoreCase("X-Fixture")) {
                request.customHeader = value;
            } else if (name.equalsIgnoreCase("Charsert")) {
                request.charsetHeader = value;
            }
        }

        byte[] body = new byte[contentLength];
        int offset = 0;
        while (offset < body.length) {
            int count = input.read(body, offset, body.length - offset);
            if (count < 0) throw new EOFException("Incomplete HTTP request body");
            offset += count;
        }
        request.body = new String(body, StandardCharsets.UTF_8);
        return request;
    }

    private static String readLine(InputStream input) throws IOException {
        ByteArrayOutputStream line = new ByteArrayOutputStream();
        int value;
        while ((value = input.read()) >= 0) {
            if (value == '\n') break;
            line.write(value);
        }
        if (value < 0 && line.size() == 0) return null;
        byte[] bytes = line.toByteArray();
        int length = bytes.length;
        if (length > 0 && bytes[length - 1] == '\r') length--;
        return new String(bytes, 0, length, StandardCharsets.ISO_8859_1);
    }

    private static void writeResponse(OutputStream output, String path) throws IOException {
        boolean redirect = path.equals("/redirect");
        byte[] body = redirect ? new byte[0] : "中文响应🙂".getBytes(StandardCharsets.UTF_8);
        int status = redirect ? 302 : path.equals("/unavailable") ? 503 : 200;
        String reason = redirect ? "Found" : status == 503 ? "Service Unavailable" : "OK";

        StringBuilder headers = new StringBuilder()
                .append("HTTP/1.1 ").append(status).append(' ').append(reason).append("\r\n")
                .append("Content-Length: ").append(body.length).append("\r\n")
                .append("Connection: close\r\n");
        if (path.equals("/set") || redirect) {
            headers.append("Set-Cookie: token=fixture; Path=/\r\n");
        }
        if (redirect) headers.append("Location: /capture\r\n");
        headers.append("Content-Type: text/plain; charset=UTF-8\r\n\r\n");

        output.write(headers.toString().getBytes(StandardCharsets.ISO_8859_1));
        output.write(body);
        output.flush();
    }

    private static final class SeenRequest {
        String method;
        String path;
        String body;
        String cookie;
        String customHeader;
        String charsetHeader;
    }
}
