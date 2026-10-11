package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.typheye.wgpro.R;

import org.json.JSONObject;

/**
 * 搜索结果关键词高亮：把命中的片段标成品牌色 + 粗体。
 *
 * <p>{@link CloudListFragment} 在渲染搜索结果的每条数据前，会往 item 里塞一个
 * {@code _highlight} 字段；各个卡片工厂用 {@link #applyFromItem} 取词高亮。</p>
 */
final class SearchHighlight {
    private SearchHighlight() { }

    static void applyFromItem(Context context, TextView view, CharSequence text, @Nullable JSONObject item) {
        String keyword = item == null ? "" : item.optString("_highlight", "");
        apply(context, view, text, keyword);
    }

    static void apply(Context context, TextView view, CharSequence text, @Nullable String keyword) {
        if (view == null) return;
        CharSequence value = highlight(context, text, keyword);
        view.setText(value);
    }

    static CharSequence highlight(Context context, CharSequence text, @Nullable String keyword) {
        if (text == null) return null;
        String source = text.toString();
        String key = keyword == null ? "" : keyword.trim();
        if (key.isEmpty() || source.isEmpty()) return text;
        String lowerSource = source.toLowerCase();
        String lowerKey = key.toLowerCase();
        SpannableString span = new SpannableString(source);
        int color = context.getColor(R.color.brand_primary);
        boolean matched = false;
        int from = 0;
        while (from <= lowerSource.length()) {
            int index = lowerSource.indexOf(lowerKey, from);
            if (index < 0) break;
            int end = index + key.length();
            span.setSpan(new BrandSpan(color), index, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            matched = true;
            from = end;
        }
        return matched ? span : text;
    }

    /** 品牌色 + 粗体（不用 StyleSpan，避免和原 TextView 的粗体叠加）。 */
    private static final class BrandSpan extends CharacterStyle {
        private final int color;
        BrandSpan(int color) { this.color = color; }

        @Override public void updateDrawState(TextPaint paint) {
            paint.setColor(color);
            paint.setTypeface(Typeface.create(paint.getTypeface(), Typeface.BOLD));
        }
    }
}
