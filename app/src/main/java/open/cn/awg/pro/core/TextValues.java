package open.cn.awg.pro.core;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/** Text and number formats used by the existing application and server protocol. */
public final class TextValues {
    private TextValues() { }

    public static String string(Object value) {
        return string(value, null);
    }

    public static String string(Object value, Object charset) {
        if (value == null) return null;
        if (value instanceof byte[]) {
            try {
                return new String((byte[]) value, charset == null
                        ? Charset.defaultCharset() : Charset.forName(charset.toString()));
            } catch (IllegalArgumentException error) { return value.toString(); }
        }
        return value.toString();
    }

    public static int intValue(Object value, int fallback) {
        try { return value instanceof Number ? ((Number) value).intValue() : Integer.parseInt(value.toString()); }
        catch (NumberFormatException | NullPointerException error) { return fallback; }
    }

    public static long longValue(Object value, long fallback) {
        try { return value instanceof Number ? ((Number) value).longValue() : Long.parseLong(value.toString()); }
        catch (NumberFormatException | NullPointerException error) { return fallback; }
    }

    public static float floatValue(Object value, float fallback) {
        try { return value instanceof Number ? ((Number) value).floatValue() : Float.parseFloat(value.toString()); }
        catch (NumberFormatException | NullPointerException error) { return fallback; }
    }

    public static double doubleValue(Object value, double fallback) {
        try { return value instanceof Number ? ((Number) value).doubleValue() : Double.parseDouble(value.toString()); }
        catch (NumberFormatException | NullPointerException error) { return fallback; }
    }

    public static boolean booleanValue(Object value, boolean fallback) {
        return value == null ? fallback : Boolean.parseBoolean(value.toString());
    }

    public static boolean startsWith(Object value, Object prefix) {
        return value != null && value.toString().startsWith(String.valueOf(prefix));
    }

    public static boolean endsWith(Object value, Object suffix) {
        return value != null && value.toString().endsWith(String.valueOf(suffix));
    }

    public static boolean contains(Object value, Object part) {
        return value != null && value.toString().contains(String.valueOf(part));
    }

    public static int indexOf(Object value, Object part) {
        return value == null ? -1 : value.toString().indexOf(String.valueOf(part));
    }

    public static int indexOf(Object value, Object part, Object start) {
        return value == null ? -1 : value.toString().indexOf(String.valueOf(part), intValue(start, 0));
    }

    public static int length(Object value) { return value == null ? -1 : value.toString().length(); }
    public static String trim(Object value) { return value == null ? null : value.toString().trim(); }
    public static String lower(Object value) { return value == null ? null : value.toString().toLowerCase(); }

    public static String replace(Object value, Object from, Object to) {
        return value == null ? null : value.toString().replace(String.valueOf(from), String.valueOf(to));
    }

    public static String replace(Object value, Object from, Object to, Object regex) {
        return Boolean.TRUE.equals(regex) && value != null
                ? value.toString().replaceAll(String.valueOf(from), String.valueOf(to))
                : replace(value, from, to);
    }

    public static String slice(Object value, Object start) {
        if (!(value instanceof String) || start == null) return null;
        String text = (String) value;
        int first;
        if (start instanceof Number) first = ((Number) start).intValue();
        else if (start instanceof String) {
            first = text.indexOf((String) start);
            if (first < 0) return null;
            first += ((String) start).length();
        } else return null;
        return first < 0 || first > text.length() ? null : text.substring(first);
    }

    public static String slice(Object value, Object start, Object end) {
        if (!(value instanceof String)) return null;
        if (start == null && end == null) return null;
        String text = (String) value;
        int first = 0;
        if (start instanceof Number) first = ((Number) start).intValue();
        else if (start instanceof String) {
            first = text.indexOf((String) start);
            if (first < 0) return null;
            first += ((String) start).length();
        } else if (start != null) return null;
        int last = text.length();
        if (end instanceof Number) last = ((Number) end).intValue();
        else if (end instanceof String) last = text.indexOf((String) end, first);
        else if (start == null) return null;
        if (first < 0 || last < first || last > text.length()) return null;
        return text.substring(first, last);
    }

    public static String[] split(Object value, Object separator) {
        return split(value, separator, false);
    }

    public static String[] split(Object value, Object separator, Object regex) {
        if (value == null) return null;
        String text = value.toString();
        if (Boolean.TRUE.equals(regex)) return text.split(String.valueOf(separator));
        String delimiters = String.valueOf(separator);
        List<String> result = new ArrayList<>();
        if (!delimiters.isEmpty()) {
            if (text.isEmpty()) return new String[0];
            int cursor = 0;
            int at;
            while ((at = text.indexOf(delimiters, cursor)) >= 0) {
                if (at > cursor) result.add(text.substring(cursor, at));
                cursor = at + delimiters.length();
            }
            result.add(text.substring(cursor));
            return result.toArray(new String[0]);
        }
        int first = -1;
        for (int i = 0; i <= text.length(); i++) {
            boolean delimiter = i == text.length() || Character.isWhitespace(text.charAt(i));
            if (delimiter) {
                if (first >= 0) result.add(text.substring(first, i));
                first = -1;
            } else if (first < 0) first = i;
        }
        return result.toArray(new String[0]);
    }

    public static long randomInclusive(Object minimum, Object maximum) {
        long first = longValue(minimum, 0);
        long last = longValue(maximum, 0);
        return (long) (Math.random() * (last + 1L - first) + first);
    }

    public static String md5(Object value) {
        if (value == null) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            if (value instanceof File) {
                try (FileInputStream input = new FileInputStream((File) value)) {
                    byte[] buffer = new byte[8192];
                    int count;
                    while ((count = input.read(buffer)) != -1) digest.update(buffer, 0, count);
                } catch (IOException error) { return null; }
            } else {
                digest.update(value instanceof byte[] ? (byte[]) value : value.toString().getBytes(StandardCharsets.UTF_8));
            }
            byte[] bytes = digest.digest();
            StringBuilder result = new StringBuilder(32);
            for (byte item : bytes) {
                result.append(Character.forDigit((item >>> 4) & 15, 16));
                result.append(Character.forDigit(item & 15, 16));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException error) { throw new AssertionError(error); }
    }

    public static java.util.regex.Matcher matcher(Object value, Object expression, Object flags) {
        return Pattern.compile(String.valueOf(expression), intValue(flags, 0)).matcher(String.valueOf(value));
    }
}
