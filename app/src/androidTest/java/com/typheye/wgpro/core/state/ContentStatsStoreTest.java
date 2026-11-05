package com.typheye.wgpro.core.state;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ContentStatsStoreTest {

    @Test
    public void appliesAppAndResourceCountsWithoutRebuildingPayload() throws Exception {
        ContentStatsStore store = ContentStatsStore.get();
        JSONArray items = new JSONArray();
        items.put(new JSONObject("{\"type\":\"app\",\"package\":\"test.stats.app\","
                + "\"downloads\":1,\"stars\":2,\"is_collected\":false}"));
        items.put(new JSONObject("{\"type\":\"resource\",\"id\":"
                + "\"0123456789abcdef0123456789abcdef\",\"download_count\":3,"
                + "\"collection_count\":4,\"is_collected\":true}"));
        store.seed(items);

        ContentStatsStore.Stats app = store.find(ContentStatsStore.TYPE_APP,
                "test.stats.app");
        ContentStatsStore.Stats resource = store.find(ContentStatsStore.TYPE_RESOURCE,
                "0123456789abcdef0123456789abcdef");
        assertEquals(1, app.downloads);
        assertEquals(2, app.stars);
        assertEquals(3, resource.downloads);
        assertEquals(4, resource.stars);
        assertTrue(resource.collected);

        store.downloadChanged(ContentStatsStore.TYPE_APP, "test.stats.app", 9);
        store.collectionChanged(ContentStatsStore.TYPE_APP, "test.stats.app", true, 7);
        assertTrue(store.apply(items));
        JSONObject appItem = items.getJSONObject(0);
        assertEquals(9, appItem.getInt("downloads"));
        assertEquals(7, appItem.getInt("collection_count"));
        assertTrue(appItem.getBoolean("is_collected"));
        assertFalse(appItem.has("_rebuilt"));
    }
}
