package open.cn.awg.pro.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** Values from the application's object and array JSON responses. */
public final class JsonValues {
    private JsonValues() { }

    public static JSONObject parseObject(Object text) {
        try { return new JSONObject(text.toString()); }
        catch (JSONException | NullPointerException error) { return null; }
    }

    public static Object value(Object json, Object key) {
        if (key == null) return null;
        if (json instanceof JSONObject) return ((JSONObject) json).opt(String.valueOf(key));
        if (json instanceof JSONArray) return ((JSONArray) json).opt(TextValues.intValue(key, -1));
        return null;
    }

    public static JSONArray array(Object json, Object key) {
        if (key == null) return null;
        return json instanceof JSONObject ? ((JSONObject) json).optJSONArray(String.valueOf(key)) : null;
    }

    public static JSONObject object(Object json, Object key) {
        if (key == null) return null;
        if (json instanceof JSONObject) return ((JSONObject) json).optJSONObject(String.valueOf(key));
        if (json instanceof JSONArray) return ((JSONArray) json).optJSONObject(TextValues.intValue(key, -1));
        return null;
    }

    public static int length(Object json) {
        if (json instanceof JSONObject) return ((JSONObject) json).length();
        if (json instanceof JSONArray) return ((JSONArray) json).length();
        return -1;
    }

    public static String stringify(Object json) {
        return json instanceof JSONObject || json instanceof JSONArray ? json.toString() : null;
    }
}
