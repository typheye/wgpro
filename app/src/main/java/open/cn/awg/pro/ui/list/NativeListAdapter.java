package open.cn.awg.pro.ui.list;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.squareup.picasso.Picasso;

import open.cn.awg.pro.core.NativeFiles;

/** Native RecyclerView adapter for ID-keyed rows used by the app's screens. */
public final class NativeListAdapter extends RecyclerView.Adapter<NativeListAdapter.RowHolder> {

    public interface RowBinder {
        void bind(View itemView, Map<Integer, Object> row, int position);
    }

    private final LayoutInflater inflater;
    private final int rowLayoutRes;
    private final RowBinder[] binders;
    private final ArrayList<Map<Integer, Object>> rows = new ArrayList<>();

    public NativeListAdapter(@NonNull android.content.Context context,
                             int rowLayoutRes,
                             @NonNull RowBinder... binders) {
        this.inflater = LayoutInflater.from(Objects.requireNonNull(context, "context"));
        if (rowLayoutRes == 0) {
            throw new IllegalArgumentException("rowLayoutRes must be a layout resource");
        }
        this.rowLayoutRes = rowLayoutRes;
        if (binders == null || binders.length == 0) {
            throw new IllegalArgumentException("at least one row binder is required");
        }
        this.binders = binders.clone();
        for (RowBinder binder : this.binders) {
            Objects.requireNonNull(binder, "binder");
        }
    }

    public static NativeListAdapter attach(@NonNull android.content.Context context,
                                           @NonNull RecyclerView list,
                                           int rowLayoutRes,
                                           @NonNull RowBinder... binders) {
        Objects.requireNonNull(context, "context");
        Objects.requireNonNull(list, "list");
        if (list.getLayoutManager() == null) {
            list.setLayoutManager(new LinearLayoutManager(context));
        }
        NativeListAdapter adapter = new NativeListAdapter(context, rowLayoutRes, binders);
        list.setAdapter(adapter);
        return adapter;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowHolder(inflater.inflate(rowLayoutRes, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        Map<Integer, Object> row = rows.get(position);
        holder.boundRow = row;
        holder.itemView.setTag(holder);
        bindValues(holder.itemView, row);
        for (RowBinder binder : binders) {
            binder.bind(holder.itemView, row, position);
        }
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    /** Appends one row. Nonpositive keys are retained as metadata and are not view IDs. */
    public boolean addRow(int[] keys, Object[] values) {
        if (keys == null || values == null || keys.length == 0 || keys.length != values.length) {
            return false;
        }
        Map<Integer, Object> row = new HashMap<>();
        for (int index = 0; index < keys.length; index++) {
            row.put(keys[index], values[index]);
        }
        rows.add(row);
        return true;
    }

    /** Removes a row map or a row at the supplied numeric/string position. */
    public boolean removeRow(Object rowOrPosition) {
        if (rowOrPosition instanceof Map) {
            return rows.remove(rowOrPosition);
        }
        try {
            int position = rowOrPosition instanceof Number
                    ? ((Number) rowOrPosition).intValue()
                    : Integer.parseInt(String.valueOf(rowOrPosition));
            if (position < 0 || position >= rows.size()) {
                return false;
            }
            rows.remove(position);
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public void clearRows() {
        rows.clear();
    }

    public Map<Integer, Object> rowAt(int position) {
        return rows.get(position);
    }

    public Object valueAt(int position, int key) {
        return rowAt(position).get(key);
    }

    public boolean replaceRow(int position, Map<Integer, ?> row) {
        if (position < 0 || position >= rows.size() || row == null) {
            return false;
        }
        rows.set(position, copyRow(row));
        return true;
    }

    public boolean setRowValue(int position, int key, Object value) {
        if (position < 0 || position >= rows.size()) {
            return false;
        }
        rows.get(position).put(key, value);
        return true;
    }

    public void replaceRows(Collection<? extends Map<Integer, ?>> replacement) {
        rows.clear();
        if (replacement != null) {
            for (Map<Integer, ?> row : replacement) {
                rows.add(copyRow(row));
            }
        }
    }

    public int rowCount() {
        return rows.size();
    }

    public void refreshRows() {
        notifyDataSetChanged();
    }

    public void refreshRow(int position) {
        notifyItemChanged(position);
    }

    public static Map<Integer, Object> rowForView(View child) {
        RowHolder holder = holderForView(child);
        if (holder == null || holder.owner == null) {
            return null;
        }
        int position = resolvedPosition(holder);
        if (position < 0 || position >= holder.owner.rows.size()) {
            return null;
        }
        return holder.owner.rows.get(position);
    }

    public static Object valueForView(View child, int key) {
        Map<Integer, Object> row = rowForView(child);
        return row == null ? null : row.get(key);
    }

    public static int positionForView(View child) {
        RowHolder holder = holderForView(child);
        return holder == null ? RecyclerView.NO_POSITION : resolvedPosition(holder);
    }

    private static RowHolder holderForView(View child) {
        View current = child;
        while (current != null) {
            Object tag = current.getTag();
            if (tag instanceof RowHolder) {
                return (RowHolder) tag;
            }
            ViewParent parent = current.getParent();
            if (parent instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) parent;
                try {
                    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(current);
                    return holder instanceof RowHolder ? (RowHolder) holder : null;
                } catch (IllegalArgumentException ignored) {
                    return null;
                }
            }
            if (!(parent instanceof View)) {
                return null;
            }
            current = (View) parent;
        }
        return null;
    }

    private static int resolvedPosition(RowHolder holder) {
        int position = holder.getBindingAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            return position >= 0 && position < holder.owner.rows.size()
                    && holder.owner.rows.get(position) == holder.boundRow
                    ? position : RecyclerView.NO_POSITION;
        }
        if (holder.boundRow == null) return RecyclerView.NO_POSITION;
        for (int index = 0; index < holder.owner.rows.size(); index++) {
            if (holder.owner.rows.get(index) == holder.boundRow) return index;
        }
        return RecyclerView.NO_POSITION;
    }

    private static Map<Integer, Object> copyRow(Map<Integer, ?> source) {
        Map<Integer, Object> copy = new HashMap<>();
        if (source != null) {
            for (Map.Entry<Integer, ?> entry : source.entrySet()) {
                copy.put(entry.getKey(), entry.getValue());
            }
        }
        return copy;
    }

    private static void bindValues(View itemView, Map<Integer, Object> row) {
        for (Map.Entry<Integer, Object> entry : row.entrySet()) {
            Integer id = entry.getKey();
            if (id == null || id <= 0) {
                continue;
            }
            View target = itemView.findViewById(id);
            if (target != null) {
                bindValue(target, entry.getValue());
            }
        }
    }

    private static void bindValue(View target, Object value) {
        if (target instanceof CompoundButton && value instanceof Boolean) {
            ((CompoundButton) target).setChecked((Boolean) value);
            return;
        }
        if (target instanceof TextView) {
            TextView textView = (TextView) target;
            if (value == null) {
                textView.setText("");
            } else if (value instanceof Integer) {
                textView.setText(target.getContext().getString((Integer) value));
            } else {
                String text = String.valueOf(value);
                if (text.startsWith("(html)")) {
                    textView.setText(Html.fromHtml(text.substring(6), Html.FROM_HTML_MODE_LEGACY));
                } else {
                    textView.setText(text);
                }
            }
            return;
        }
        if (target instanceof ImageView) {
            bindImage((ImageView) target, value);
        }
    }

    private static void bindImage(ImageView target, Object value) {
        Picasso.get().cancelRequest(target);
        if (value == null) {
            target.setImageDrawable(null);
        } else if (value instanceof Number) {
            target.setImageResource(((Number) value).intValue());
        } else if (value instanceof Drawable) {
            target.setImageDrawable((Drawable) value);
        } else if (value instanceof Bitmap) {
            target.setImageDrawable(new BitmapDrawable(target.getResources(), (Bitmap) value));
        } else if (value instanceof ImageView) {
            target.setImageDrawable(((ImageView) value).getDrawable());
        } else if (value instanceof Uri) {
            target.setImageURI((Uri) value);
        } else {
            loadImage(target, String.valueOf(value));
        }
    }

    private static void loadImage(ImageView target, String source) {
        if (source.isEmpty()) {
            target.setImageDrawable(null);
            return;
        }
        target.setImageDrawable(null);
        if (source.charAt(0) == '#') {
            target.setImageDrawable(new ColorDrawable(Color.parseColor(source)));
            return;
        }
        if (source.matches("[0-9]+")) {
            Picasso.get().load(Integer.parseInt(source)).into(target);
            return;
        }

        Uri uri = Uri.parse(source);
        String scheme = uri.getScheme();
        if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
            Picasso.get().load(uri).into(target);
            return;
        }
        if ("content".equalsIgnoreCase(scheme) || "file".equalsIgnoreCase(scheme)) {
            Picasso.get().load(uri).into(target);
            return;
        }
        if (source.startsWith("@")) {
            String assetPath = NativeFiles.resolvePath(target.getContext(), source);
            Picasso.get().load(Uri.parse("file:///android_asset/" + Uri.encode(assetPath, "/"))).into(target);
            return;
        }
        String path = NativeFiles.resolvePath(target.getContext(), source);
        Picasso.get().load(new java.io.File(path)).into(target);
    }

    static final class RowHolder extends RecyclerView.ViewHolder {
        final NativeListAdapter owner;
        Map<Integer, Object> boundRow;

        RowHolder(@NonNull View itemView, NativeListAdapter owner) {
            super(itemView);
            this.owner = owner;
        }
    }

}
