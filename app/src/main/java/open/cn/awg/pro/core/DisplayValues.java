package open.cn.awg.pro.core;

import android.content.Context;

/** Density conversion for the existing watch layouts. */
public final class DisplayValues {
    private DisplayValues() { }

    public static int dpToPx(Context context, Object value) {
        try { return (int) (integer(value) * context.getResources().getDisplayMetrics().density + 0.5f); }
        catch (NumberFormatException | NullPointerException error) { return -1; }
    }

    public static int pxToDp(Context context, Object value) {
        try { return (int) (integer(value) / context.getResources().getDisplayMetrics().density + 0.5f); }
        catch (NumberFormatException | NullPointerException error) { return -1; }
    }

    public static int spToPx(Context context, Object value) {
        try { return (int) (integer(value) * context.getResources().getDisplayMetrics().scaledDensity + 0.5f); }
        catch (NumberFormatException | NullPointerException error) { return -1; }
    }

    public static int pxToSp(Context context, Object value) {
        try { return (int) (integer(value) / context.getResources().getDisplayMetrics().scaledDensity + 0.5f); }
        catch (NumberFormatException | NullPointerException error) { return -1; }
    }

    private static int integer(Object value) {
        return value instanceof Number ? ((Number) value).intValue() : Integer.parseInt(value.toString());
    }
    public static int systemBarHeight(Context context, String name) {
        int id = context.getResources().getIdentifier(name, "dimen", "android");
        return id > 0 ? context.getResources().getDimensionPixelSize(id) : 0;
    }
}
