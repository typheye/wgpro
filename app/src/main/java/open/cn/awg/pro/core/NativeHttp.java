package open.cn.awg.pro.core;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class NativeHttp {
    private static CookieManager cookieManager;

    private NativeHttp() {
    }

    public static Response get(String url, String charsetName, String cookie,
                               boolean useCookieStore, Map<String, String> headers,
                               int connectTimeoutMillis, int readTimeoutMillis, String proxy) {
        return execute(url, "GET", null, null, charsetName, cookie, useCookieStore,
                headers, connectTimeoutMillis, readTimeoutMillis, proxy);
    }

    public static Response postForm(String url, String[] fields, String charsetName, String cookie,
                                    boolean useCookieStore, Map<String, String> headers,
                                    int connectTimeoutMillis, int readTimeoutMillis, String proxy) {
        if (fields == null) {
            return get(url, charsetName, cookie, useCookieStore, headers,
                    connectTimeoutMillis, readTimeoutMillis, proxy);
        }
        Charset charset = charset(charsetName);
        byte[] body;
        try {
            body = encodeForm(fields, charset).getBytes(charset);
        } catch (IOException exception) {
            return Response.failed(exception);
        }
        return execute(url, "POST", body, "application/x-www-form-urlencoded", charset.name(),
                cookie, useCookieStore, headers, connectTimeoutMillis, readTimeoutMillis, proxy);
    }

    public static Response postJson(String url, String json, String charsetName, String cookie,
                                    boolean useCookieStore, Map<String, String> headers,
                                    int connectTimeoutMillis, int readTimeoutMillis, String proxy) {
        if (json == null) {
            return get(url, charsetName, cookie, useCookieStore, headers,
                    connectTimeoutMillis, readTimeoutMillis, proxy);
        }
        Charset charset = charset(charsetName);
        Map<String, String> requestHeaders = new LinkedHashMap<>();
        if (headers != null) {
            requestHeaders.putAll(headers);
        }
        requestHeaders.put("Charsert", charset.name());
        String requestBody = json + System.lineSeparator();
        return execute(url, "POST", requestBody.getBytes(charset),
                "application/json; charset=" + charset.name(), charset.name(),
                cookie, useCookieStore, requestHeaders, connectTimeoutMillis, readTimeoutMillis, proxy);
    }

    public static String encodeForm(String[] fields, String charsetName) throws IOException {
        return encodeForm(fields, charset(charsetName));
    }

    public static Map<String, String> parseHeaderBlock(String headerBlock) {
        if (headerBlock == null || headerBlock.length() == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> result = new LinkedHashMap<>();
        String[] lines = headerBlock.split("\\|\\|");
        for (String line : lines) {
            int separator = line.indexOf('=');
            if (separator < 0) {
                continue;
            }
            String name = line.substring(0, separator).trim();
            if (name.length() == 0) {
                continue;
            }
            result.put(name, line.substring(separator + 1).trim());
        }
        return result;
    }

    public static String cookieHeader(String url) {
        URI uri;
        try {
            uri = new URI(normalizeUrl(url));
        } catch (Exception exception) {
            return null;
        }
        try {
            Map<String, List<String>> headers = cookieManager().get(uri, Collections.emptyMap());
            List<String> cookies = headers.get("Cookie");
            if (cookies == null) {
                cookies = headers.get("Cookie2");
            }
            if (cookies == null || cookies.isEmpty()) {
                return null;
            }
            return join(cookies, "; ");
        } catch (IOException exception) {
            return null;
        }
    }

    public static void clearCookies() {
        synchronized (NativeHttp.class) {
            cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        }
    }

    public static int download(Context context, String rawUrl, String path, boolean overwrite,
                               String[] fields, String charsetName, String cookie,
                               boolean useCookieStore, String headerBlock,
                               int connectTimeoutMillis, int readTimeoutMillis, String proxy) {
        Charset requestCharset = charset(charsetName);
        File target = NativeFiles.file(context, path);
        if (target.isDirectory()) return -1;
        File parent = target.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.isDirectory()) {
            return -1;
        }
        File temporary = new File(parent, new Random().nextLong() + ".xzz");
        String currentUrl = normalizeUrl(rawUrl);
        String currentMethod = fields == null ? "GET" : "POST";
        String currentCookie = cookie;
        boolean cookieJarEnabled = useCookieStore;
        boolean redirected = false;
        byte[] formBody;
        try {
            formBody = fields == null ? null : encodeForm(fields, requestCharset).getBytes(requestCharset);
        } catch (IOException exception) {
            return -1;
        }
        Map<String, String> headers = parseHeaderBlock(headerBlock);

        for (int redirect = 0; redirect <= 8; redirect++) {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(currentUrl);
                connection = open(url, proxy);
                connection.setUseCaches(false);
                connection.setInstanceFollowRedirects(false);
                connection.setConnectTimeout(connectTimeoutMillis);
                connection.setReadTimeout(readTimeoutMillis);
                connection.setRequestProperty("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
                connection.setRequestProperty("Accept-Encoding", "identity");
                if (!redirected) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        if (header.getKey() != null && header.getValue() != null) {
                            connection.setRequestProperty(header.getKey(), header.getValue());
                        }
                    }
                }
                if (currentCookie != null) {
                    connection.setRequestProperty("Cookie", currentCookie);
                } else if (cookieJarEnabled) {
                    addStoredCookies(connection, url);
                }
                connection.setRequestMethod(currentMethod);
                if (formBody != null) {
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    try (OutputStream output = connection.getOutputStream()) {
                        output.write(formBody);
                        output.flush();
                    }
                }

                int status = connection.getResponseCode();
                Map<String, List<String>> responseHeaders = copyHeaders(connection.getHeaderFields());
                String location = connection.getHeaderField("Location");
                boolean redirectResponse = status >= 300 && status < 400;
                if (redirectResponse || useCookieStore) {
                    storeCookies(url, responseHeaders);
                }
                if (redirectResponse && location != null && redirect < 8) {
                    URL next = new URL(url, location);
                    currentUrl = normalizeUrl(next.toString());
                    currentMethod = "GET";
                    formBody = null;
                    currentCookie = null;
                    cookieJarEnabled = true;
                    redirected = true;
                    connection.disconnect();
                    continue;
                }
                if (status != HttpURLConnection.HTTP_OK) {
                    return -1;
                }

                InputStream input = connection.getInputStream();
                int contentLength = connection.getContentLength();
                if (target.exists() && !overwrite
                        && (input.available() == target.length() || contentLength == target.length())) {
                    input.close();
                    return 1;
                }
                try (InputStream response = input; OutputStream output = new FileOutputStream(temporary, false)) {
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = response.read(buffer)) != -1) {
                        output.write(buffer, 0, count);
                    }
                }
                if (target.exists() && !target.delete()) {
                    throw new IOException("Cannot replace download destination");
                }
                if (!temporary.renameTo(target)) {
                    throw new IOException("Cannot move completed download to destination");
                }
                return 0;
            } catch (Exception exception) {
                temporary.delete();
                return -1;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        temporary.delete();
        return -1;
    }

    private static Response execute(String rawUrl, String initialMethod, byte[] initialBody,
                                    String initialContentType, String charsetName, String explicitCookie,
                                    boolean useCookieStore, Map<String, String> headers,
                                    int connectTimeoutMillis, int readTimeoutMillis, String proxy) {
        Charset responseCharset = charset(charsetName);
        String currentUrl = normalizeUrl(rawUrl);
        String currentMethod = initialMethod;
        byte[] currentBody = initialBody;
        String currentContentType = initialContentType;
        String currentExplicitCookie = explicitCookie;
        boolean cookieJarEnabled = useCookieStore;
        boolean redirected = false;

        for (int redirect = 0; redirect <= 8; redirect++) {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(currentUrl);
                connection = open(url, proxy);
                connection.setUseCaches(false);
                connection.setInstanceFollowRedirects(false);
                connection.setConnectTimeout(timeout(connectTimeoutMillis));
                connection.setReadTimeout(timeout(readTimeoutMillis));
                connection.setRequestProperty("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
                connection.setRequestProperty("Accept-Encoding", "identity");

                if (!redirected && headers != null) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        if (header.getKey() != null && header.getValue() != null) {
                            connection.setRequestProperty(header.getKey(), header.getValue());
                        }
                    }
                }
                if (currentExplicitCookie != null) {
                    connection.setRequestProperty("Cookie", currentExplicitCookie);
                } else if (cookieJarEnabled) {
                    addStoredCookies(connection, url);
                }

                connection.setRequestMethod(currentMethod);
                if (currentBody != null) {
                    connection.setDoOutput(true);
                    if (currentContentType != null) {
                        connection.setRequestProperty("Content-Type", currentContentType);
                    }
                    try (java.io.OutputStream output = connection.getOutputStream()) {
                        output.write(currentBody);
                        output.flush();
                    }
                }

                int status = connection.getResponseCode();
                Map<String, List<String>> responseHeaders = copyHeaders(connection.getHeaderFields());

                String location = connection.getHeaderField("Location");
                boolean redirectResponse = status >= 300 && status < 400;
                if (redirectResponse || useCookieStore) {
                    storeCookies(url, responseHeaders);
                }
                if (redirectResponse && location != null && redirect < 8) {
                    URL next = new URL(url, location);
                    currentUrl = normalizeUrl(next.toString());
                    currentMethod = "GET";
                    currentBody = null;
                    currentContentType = null;
                    currentExplicitCookie = null;
                    cookieJarEnabled = true;
                    redirected = true;
                    connection.disconnect();
                    continue;
                }

                InputStream responseStream = status >= 400
                        ? connection.getErrorStream() : connection.getInputStream();
                String responseBody = responseStream == null
                        ? null : new String(readAll(responseStream), responseCharset);
                return new Response(status, responseBody, responseHeaders, null);
            } catch (Exception exception) {
                return Response.failed(exception);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        return Response.failed(new IOException("Too many redirects"));
    }

    private static HttpURLConnection open(URL url, String proxyAddress) throws IOException {
        if (proxyAddress == null || proxyAddress.trim().length() == 0) {
            return (HttpURLConnection) url.openConnection();
        }
        String value = proxyAddress.trim();
        int separator = value.lastIndexOf(':');
        String host = separator < 0 ? value : value.substring(0, separator);
        int port = separator < 0 ? 80 : Integer.parseInt(value.substring(separator + 1));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        return (HttpURLConnection) url.openConnection(proxy);
    }

    private static void addStoredCookies(HttpURLConnection connection, URL url) {
        try {
            URI uri = url.toURI();
            Map<String, List<String>> headers = cookieManager().get(uri, Collections.emptyMap());
            for (Map.Entry<String, List<String>> header : headers.entrySet()) {
                String value = join(header.getValue(), "; ");
                if (value.length() > 0) {
                    connection.setRequestProperty(header.getKey(), value);
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static void storeCookies(URL url, Map<String, List<String>> headers) {
        try {
            cookieManager().put(url.toURI(), headers);
        } catch (Exception ignored) {
        }
    }

    private static synchronized CookieManager cookieManager() {
        if (cookieManager == null) {
            cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        }
        return cookieManager;
    }

    private static String encodeForm(String[] fields, Charset charset) throws IOException {
        StringBuilder body = new StringBuilder();
        for (String field : fields) {
            if (field == null) {
                continue;
            }
            int separator = field.indexOf('=');
            if (separator < 0) {
                continue;
            }
            if (body.length() > 0) {
                body.append('&');
            }
            body.append(field, 0, separator)
                    .append('=')
                    .append(encodeFormValue(field.substring(separator + 1), charset));
        }
        return body.toString();
    }

    private static String encodeFormValue(String value, Charset charset) throws IOException {
        String encoded = URLEncoder.encode(value, charset.name());
        return encoded.replace("+", "%20").replace("%2A", "*");
    }

    private static Charset charset(String charsetName) {
        return Charset.forName(charsetName == null ? StandardCharsets.UTF_8.name() : charsetName);
    }

    private static int timeout(int value) {
        return value;
    }

    private static String normalizeUrl(String url) {
        if (url == null) {
            return null;
        }
        return url.replace(" ", "%20").replace("\\&", "%26");
    }

    private static byte[] readAll(InputStream input) throws IOException {
        try (InputStream stream = input; ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int count;
            while ((count = stream.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
            return output.toByteArray();
        }
    }

    private static Map<String, List<String>> copyHeaders(Map<String, List<String>> source) {
        if (source == null) {
            return Collections.emptyMap();
        }
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> header : source.entrySet()) {
            List<String> values = header.getValue();
            result.put(header.getKey(), values == null
                    ? Collections.emptyList()
                    : Collections.unmodifiableList(new ArrayList<>(values)));
        }
        return Collections.unmodifiableMap(result);
    }

    private static String join(List<String> values, String separator) {
        StringBuilder result = new StringBuilder();
        for (String value : values) {
            if (value == null || value.length() == 0) {
                continue;
            }
            if (result.length() > 0) {
                result.append(separator);
            }
            result.append(value);
        }
        return result.toString();
    }

    public static final class Response {
        public final int statusCode;
        public final String body;
        public final Map<String, List<String>> headers;
        public final Exception error;

        private Response(int statusCode, String body, Map<String, List<String>> headers, Exception error) {
            this.statusCode = statusCode;
            this.body = body;
            this.headers = headers;
            this.error = error;
        }

        private static Response failed(Exception error) {
            return new Response(-1, null, Collections.emptyMap(), error);
        }

        public boolean isSuccessful() {
            return statusCode >= 200 && statusCode < 300;
        }

        public String bodyForHttp200() {
            return statusCode == HttpURLConnection.HTTP_OK ? body : null;
        }
    }
}