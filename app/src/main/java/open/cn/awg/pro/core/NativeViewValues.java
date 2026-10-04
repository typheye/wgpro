package open.cn.awg.pro.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import open.cn.awg.pro.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/** Native view values shared by settings screens and their generated extension controls. */
public final class NativeViewValues {
    private NativeViewValues() { }

    public static View findView(View root, Object value) {
        if (value instanceof View) return (View) value;
        if (root == null || value == null) return null;
        if (value instanceof Number) return root.findViewById(((Number) value).intValue());
        String id = value.toString();
        try { return root.findViewById(Integer.parseInt(id)); }
        catch (NumberFormatException ignored) {
            int resource = root.getResources().getIdentifier(id, "id", root.getContext().getPackageName());
            return root.findViewById(resource);
        }
    }

    public static int dimension(Context context, Object value) {
        if (value instanceof Number) return ((Number) value).intValue();
        String text = value.toString();
        if (text.endsWith("dip")) return (int) (Float.parseFloat(text.substring(0, text.length() - 3)) * context.getResources().getDisplayMetrics().density + .5f);
        if (text.endsWith("dp")) return (int) (Float.parseFloat(text.substring(0, text.length() - 2)) * context.getResources().getDisplayMetrics().density + .5f);
        if (text.endsWith("sp")) return (int) (Float.parseFloat(text.substring(0, text.length() - 2)) * context.getResources().getDisplayMetrics().scaledDensity + .5f);
        return Integer.parseInt(text);
    }

    public static int color(Object value) {
        try { return value instanceof Number ? ((Number) value).intValue() : Color.parseColor(value.toString()); }
        catch (IllegalArgumentException | NullPointerException error) { return 0; }
    }

    public static String colorResourceString(Context context, @ColorRes int resourceId) {
        return String.format(Locale.ROOT, "#%08x", ContextCompat.getColor(context, resourceId));
    }

    public static int gravity(Object value) {
        if (value instanceof Number) return ((Number) value).intValue();
        int gravity = 0;
        for (String part : value.toString().split("\\|")) {
            switch (part.trim()) {
                case "center": gravity |= Gravity.CENTER; break;
                case "center_horizontal": gravity |= Gravity.CENTER_HORIZONTAL; break;
                case "center_vertical": gravity |= Gravity.CENTER_VERTICAL; break;
                case "top": gravity |= Gravity.TOP; break;
                case "bottom": gravity |= Gravity.BOTTOM; break;
                case "left": gravity |= Gravity.LEFT; break;
                case "right": gravity |= Gravity.RIGHT; break;
                case "start": gravity |= Gravity.START; break;
                case "end": gravity |= Gravity.END; break;
                default: gravity |= TextValues.intValue(part.trim(), 0);
            }
        }
        return gravity;
    }

    public static void size(View view, Object width, Object height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) params = new ViewGroup.MarginLayoutParams(-2, -2);
        params.width = dimension(view.getContext(), width);
        params.height = dimension(view.getContext(), height);
        view.setLayoutParams(params);
    }

    public static void width(View view, Object width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        size(view, width, params == null ? -2 : params.height);
    }

    public static void height(View view, Object height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        size(view, params == null ? -2 : params.width, height);
    }

    public static void margins(View view, Object left, Object top, Object right, Object bottom) {
        ViewGroup.LayoutParams current = view.getLayoutParams();
        ViewGroup.MarginLayoutParams params = current instanceof ViewGroup.MarginLayoutParams
                ? (ViewGroup.MarginLayoutParams) current : new ViewGroup.MarginLayoutParams(current == null ? new ViewGroup.LayoutParams(-2, -2) : current);
        Context context = view.getContext();
        params.setMargins(dimension(context, left), dimension(context, top), dimension(context, right), dimension(context, bottom));
        view.setLayoutParams(params);
    }

    public static void margin(View view, int side, Object value) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int pixels = dimension(view.getContext(), value);
        switch (side) {
            case 0: params.leftMargin = pixels; break;
            case 1: params.topMargin = pixels; break;
            case 2: params.rightMargin = pixels; break;
            case 3: params.bottomMargin = pixels; break;
            default: throw new IllegalArgumentException("margin side");
        }
        view.setLayoutParams(params);
    }

    public static void padding(View view, Object left, Object top, Object right, Object bottom) {
        Context context = view.getContext();
        view.setPadding(dimension(context, left), dimension(context, top), dimension(context, right), dimension(context, bottom));
    }

    public static void spinner(Spinner spinner, Object values) {
        List<?> items = values instanceof List ? (List<?>) values : values instanceof Object[]
                ? Arrays.asList((Object[]) values) : java.util.Collections.singletonList(values);
        ArrayAdapter<?> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // Existing account screens require on-device password entry; retain their authentication flow.
    @android.annotation.SuppressLint("WearPasswordInput")
    public static int inputType(Object value) {
        if (value instanceof Number) return ((Number) value).intValue();
        int flags = 0;
        for (String part : value.toString().split("\\|")) {
            switch (part.trim()) {
                case "none": break;
                case "text": flags |= InputType.TYPE_CLASS_TEXT; break;
                case "number": flags |= InputType.TYPE_CLASS_NUMBER; break;
                case "phone": flags |= InputType.TYPE_CLASS_PHONE; break;
                case "numberdecimal": flags |= InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL; break;
                case "numbersigned": flags |= InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED; break;
                case "textpassword": flags |= InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD; break;
                case "textmultiline": flags |= InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE; break;
                case "textemailaddress": flags |= InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS; break;
                case "texturi": flags |= InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI; break;
                default: flags |= TextValues.intValue(part.trim(), 0);
            }
        }
        return flags;
    }

    public static void typeface(TextView text, Object value) {
        String name = value.toString();
        Typeface face;
        switch (name) {
            case "sans": face = Typeface.SANS_SERIF; break;
            case "serif": face = Typeface.SERIF; break;
            case "monospace": face = Typeface.MONOSPACE; break;
            default:
                if (name.startsWith("@")) face = Typeface.createFromAsset(text.getContext().getAssets(), name.substring(1));
                else if (name.startsWith("%") || name.startsWith("$") || name.startsWith("/")) face = Typeface.createFromFile(NativeFiles.resolvePath(text.getContext(), name));
                else face = null;
        }
        text.setTypeface(face);
    }

    public static int textStyle(Object value) {
        switch (value.toString()) {
            case "bold": return Typeface.BOLD;
            case "italic": return Typeface.ITALIC;
            case "bolditalic": return Typeface.BOLD_ITALIC;
            default: return Typeface.NORMAL;
        }
    }

    public static Drawable drawable(Context context, Object value) {
        if (value == null) return null;
        if (value instanceof Drawable) return (Drawable) value;
        if (value instanceof Bitmap) return new BitmapDrawable(context.getResources(), (Bitmap) value);
        if (value instanceof ImageView) return ((ImageView) value).getDrawable();
        if (value instanceof Number) return ContextCompat.getDrawable(context, ((Number) value).intValue());
        String text = value.toString();
        if (text.startsWith("#")) return new ColorDrawable(Color.parseColor(text));
        if (text.equals("white") || text.equals("black") || text.equals("through")) {
            int pressed = Color.parseColor(text.equals("white") ? "#e6eaf7" : text.equals("black") ? "#202020" : "#10000000");
            int normal = Color.parseColor(text.equals("white") ? "#ffffff" : text.equals("black") ? "#000000" : "#00000000");
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, new ColorDrawable(pressed));
            states.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, new ColorDrawable(pressed));
            states.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(normal));
            states.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(pressed));
            states.addState(new int[]{}, new ColorDrawable(normal));
            return states;
        }
        if (text.matches("[0-9]+")) return ContextCompat.getDrawable(context, Integer.parseInt(text));
        if (text.equals("selectableitem") || text.equals("selectableitemborderless")) {
            TypedValue attribute = new TypedValue();
            context.getTheme().resolveAttribute(text.equals("selectableitem") ? android.R.attr.selectableItemBackground : android.R.attr.selectableItemBackgroundBorderless, attribute, true);
            return ContextCompat.getDrawable(context, attribute.resourceId);
        }
        if (text.startsWith("@")) {
            try (InputStream input = context.getAssets().open(text.substring(1))) {
                return Drawable.createFromStream(input, null);
            } catch (IOException error) { return null; }
        }
        return Drawable.createFromPath(NativeFiles.resolvePath(context, text));
    }

    public static void background(final View view, Object value) {
        Object previous = view.getTag(R.id.native_background_target);
        if (previous instanceof Target) Picasso.get().cancelRequest((Target) previous);
        view.setTag(R.id.native_background_target, null);
        String path = value instanceof String ? (String) value : "";
        if (path.startsWith("http:") || path.startsWith("https:") || path.startsWith("ftp:")) {
            Target target = new Target() {
                @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    if (view.getTag(R.id.native_background_target) == this) {
                        view.setBackground(new BitmapDrawable(view.getResources(), bitmap));
                        view.setTag(R.id.native_background_target, null);
                    }
                }
                @Override public void onBitmapFailed(Exception error, Drawable drawable) {
                    if (view.getTag(R.id.native_background_target) == this) view.setTag(R.id.native_background_target, null);
                }
                @Override public void onPrepareLoad(Drawable drawable) { }
            };
            view.setTag(R.id.native_background_target, target);
            Picasso.get().load(path).into(target);
        } else view.setBackground(drawable(view.getContext(), value));
    }

    public static void image(ImageView view, Object value) {
        Picasso.get().cancelRequest(view);
        if (value == null) { view.setImageDrawable(null); return; }
        if (value instanceof Drawable) { view.setImageDrawable((Drawable) value); return; }
        if (value instanceof Bitmap) { view.setImageBitmap((Bitmap) value); return; }
        if (value instanceof ImageView) { view.setImageDrawable(((ImageView) value).getDrawable()); return; }
        if (value instanceof Number) { view.setImageResource(((Number) value).intValue()); return; }
        String path = value.toString();
        if (path.startsWith("#")) { view.setImageDrawable(new ColorDrawable(Color.parseColor(path))); return; }
        if (path.matches("[0-9]+")) { view.setImageResource(Integer.parseInt(path)); return; }
        Uri uri = value instanceof Uri ? (Uri) value : Uri.parse(path);
        if (uri.getScheme() != null) { Picasso.get().load(uri).into(view); return; }
        if (path.startsWith("@")) uri = Uri.parse("file:///android_asset/" + Uri.encode(path.substring(1), "/"));
        else uri = Uri.fromFile(new java.io.File(NativeFiles.resolvePath(view.getContext(), path)));
        Picasso.get().load(uri).into(view);
    }

    public static int lastVisible(RecyclerView view) {
        RecyclerView.LayoutManager manager = view.getLayoutManager();
        return manager instanceof LinearLayoutManager ? ((LinearLayoutManager) manager).findLastVisibleItemPosition() : -1;
    }

    public static void scrollPosition(RecyclerView view, Object position) {
        int number;
        if (position instanceof Number) number = ((Number) position).intValue();
        else {
            switch (position.toString().toLowerCase(java.util.Locale.ROOT)) {
                case "top": number = view.getTop(); break;
                case "bottom": number = view.getBottom(); break;
                case "left": number = view.getLeft(); break;
                case "right": number = view.getRight(); break;
                default: number = Integer.parseInt(position.toString());
            }
        }
        view.smoothScrollToPosition(number);
    }

    public static void relativeRule(View view, RelativeLayout.LayoutParams params, int rule, Object enabled) {
        params.addRule(rule, Boolean.TRUE.equals(enabled) ? RelativeLayout.TRUE : 0);
        view.setLayoutParams(params);
    }

    public static LinearLayout.LayoutParams linearParams(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof LinearLayout.LayoutParams) return (LinearLayout.LayoutParams) params;
        LinearLayout.LayoutParams result = new LinearLayout.LayoutParams(-2, -2);
        view.setLayoutParams(result);
        return result;
    }

    public static RelativeLayout.LayoutParams relativeParams(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof RelativeLayout.LayoutParams) return (RelativeLayout.LayoutParams) params;
        RelativeLayout.LayoutParams result = new RelativeLayout.LayoutParams(-2, -2);
        view.setLayoutParams(result);
        return result;
    }

    public static void weight(View view, LinearLayout.LayoutParams params, Object weight) {
        params.weight = TextValues.intValue(weight, -1);
        view.setLayoutParams(params);
    }
}
