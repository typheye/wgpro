package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索历史（本地 SharedPreferences，按登录账号隔离），最多 {@link #MAX} 条、去重、最新在前。
 */
final class SearchHistoryStore {
    private static final String PREF = "search_history";
    private static final int MAX = 100;

    private final SharedPreferences prefs;
    private final String key;

    SearchHistoryStore(@NonNull Context context, @Nullable String uid) {
        this.prefs = context.getApplicationContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        this.key = "uid_" + (uid == null || uid.trim().isEmpty() ? "guest" : uid.trim());
    }

    @NonNull List<String> list() {
        String raw = prefs.getString(key, "");
        List<String> items = new ArrayList<>();
        if (raw == null || raw.isEmpty()) return items;
        for (String part : raw.split("\n")) {
            String value = part.trim();
            if (!value.isEmpty()) items.add(value);
        }
        return items;
    }

    /** 提交搜索时写入：已存在就提到最前，超出上限丢最旧。 */
    void add(@Nullable String query) {
        String value = query == null ? "" : query.trim();
        if (value.isEmpty()) return;
        List<String> items = list();
        items.remove(value);
        items.add(0, value);
        while (items.size() > MAX) items.remove(items.size() - 1);
        save(items);
    }

    void remove(@Nullable String query) {
        String value = query == null ? "" : query.trim();
        List<String> items = list();
        if (items.remove(value)) save(items);
    }

    void clear() {
        prefs.edit().remove(key).apply();
    }

    private void save(@NonNull List<String> items) {
        StringBuilder builder = new StringBuilder();
        for (String item : items) {
            if (builder.length() > 0) builder.append('\n');
            builder.append(item.replace('\n', ' '));
        }
        prefs.edit().putString(key, builder.toString()).apply();
    }
}
