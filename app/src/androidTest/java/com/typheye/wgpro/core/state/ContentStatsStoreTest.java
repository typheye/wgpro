package com.typheye.wgpro.core.state;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ContentStatsStoreTest {

    @Test
    public void appliesAppAndResourceCountsWithoutRebuildingPayload() throws Exception {
        ContentStatsStore store = ContentStatsStore.get();
        Context context = ApplicationProvider.getApplicationContext();
        JSONArray items = new JSONArray();
        items.put(new JSONObject("{\"type\":\"app\",\"package\":\"test.stats.app\","
                + "\"downloads\":1,\"stars\":2,\"is_collected\":false,"
                + "\"personalized\":true}"));
        items.put(new JSONObject("{\"type\":\"resource\",\"id\":"
                + "\"0123456789abcdef0123456789abcdef\",\"download_count\":3,"
                + "\"collection_count\":4,\"is_collected\":true,"
                + "\"personalized\":true}"));
        items.put(new JSONObject("{\"type\":\"app\",\"id\":\"catalog.only.id\","
                + "\"package\":\"config.package.name\",\"downloads\":5,\"stars\":1,"
                + "\"is_collected\":false,\"personalized\":true}"));
        items.put(new JSONObject("{\"id\":\"fedcba9876543210fedcba9876543210\","
                + "\"title\":\"测试资源\",\"downloads\":6,\"download_count\":6,"
                + "\"stars\":2,\"collection_count\":2,\"is_collected\":false,"
                + "\"personalized\":true}"));
        store.seed(context, items);

        ContentStatsStore.Stats app = store.find(context, ContentStatsStore.TYPE_APP,
                "test.stats.app");
        ContentStatsStore.Stats resource = store.find(context, ContentStatsStore.TYPE_RESOURCE,
                "0123456789abcdef0123456789abcdef");
        assertEquals(1, app.downloads);
        assertEquals(2, app.stars);
        assertEquals(3, resource.downloads);
        assertEquals(4, resource.stars);
        assertTrue(resource.collected);
        assertEquals(5, store.find(context, ContentStatsStore.TYPE_APP,
                "catalog.only.id").downloads);
        assertNull(store.find(context, ContentStatsStore.TYPE_APP,
                "config.package.name"));
        assertEquals(6, store.find(context, ContentStatsStore.TYPE_RESOURCE,
                "fedcba9876543210fedcba9876543210").downloads);

        store.downloadChanged(context, ContentStatsStore.TYPE_APP, "test.stats.app", 9);
        store.collectionChanged(context, ContentStatsStore.TYPE_APP, "test.stats.app", true, 7);
        assertTrue(app.collectedKnown);
        assertTrue(store.apply(context, items));
        JSONObject appItem = items.getJSONObject(0);
        assertEquals(9, appItem.getInt("downloads"));
        assertEquals(7, appItem.getInt("collection_count"));
        assertTrue(appItem.getBoolean("is_collected"));
        assertFalse(appItem.has("_rebuilt"));

        store.downloadChanged(context, ContentStatsStore.TYPE_APP, "not.seeded.app", -1);
        assertNull(store.find(context, ContentStatsStore.TYPE_APP, "not.seeded.app"));
    }
}
