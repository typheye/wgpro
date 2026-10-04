package open.cn.awg.pro.core;

import org.junit.Test;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import static org.junit.Assert.*;

public class TextValuesTest {
    @Test public void messageMarkersExtractOnlyPayload() {
        String message = "说明[PHOTO]https://example.invalid/image.png[P:END]尾部";
        assertTrue(TextValues.contains(message, "[PHOTO]"));
        assertEquals("https://example.invalid/image.png", TextValues.slice(message, "[PHOTO]", "[P:END]"));
        assertNull(TextValues.slice(message, "[AUDIO]", "[A:END]"));
        assertNull(TextValues.slice(null, "[PHOTO]", "[P:END]"));
    }

    @Test public void literalSeparatorsPreserveProtocolBehavior() {
        assertArrayEquals(new String[]{"a", "b", ""}, TextValues.split("a||b||||", "||"));
        assertArrayEquals(new String[]{"a|b"}, TextValues.split("a|b", "||"));
        assertArrayEquals(new String[]{"a", "b"}, TextValues.split(" a\t b ", ""));
        assertArrayEquals(new String[]{"a", "b"}, TextValues.split("a||b||", "\\|\\|", true));
    }

    @Test public void numericSettingsRetainFallbackAndLongPrecision() {
        assertEquals(-1, TextValues.intValue(null, -1));
        assertEquals(-1, TextValues.intValue("12.5", -1));
        assertEquals(12, TextValues.intValue(12.5, -1));
        assertEquals(9007199254740993L, TextValues.longValue("9007199254740993", -1));
        assertEquals(-1L, TextValues.longValue("overflow", -1));
        assertTrue(TextValues.booleanValue(null, true));
        assertFalse(TextValues.booleanValue("1", true));
    }

    @Test public void invalidSliceBoundsReturnNull() {
        assertNull(TextValues.slice("abc", 2, 1));
        assertNull(TextValues.slice("abc", -1));
        assertNull(TextValues.slice("abc", 0, 4));
        assertNull(TextValues.slice(123, 0, 1));
        assertEquals("", TextValues.slice("abc", 3));
        assertEquals("ab", TextValues.slice("abc", null, 2));
    }

    @Test public void utf8TextAndChecksumsStayStable() throws Exception {
        byte[] encoded = "中文消息🙂".getBytes(StandardCharsets.UTF_8);
        assertEquals("中文消息🙂", TextValues.string(encoded, "UTF-8"));
        assertEquals("900150983cd24fb0d6963f7d28e17f72", TextValues.md5("abc"));
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", TextValues.md5(new byte[0]));
        assertNull(TextValues.md5(null));
        File sample = File.createTempFile("native-checksum", ".bin");
        try {
            Files.write(sample.toPath(), encoded);
            assertEquals(TextValues.md5(encoded), TextValues.md5(sample));
        } finally {
            Files.delete(sample.toPath());
        }
    }
}
