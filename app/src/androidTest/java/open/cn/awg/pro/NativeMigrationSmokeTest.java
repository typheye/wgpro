package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.app.Activity;
import android.app.Instrumentation;
import android.widget.SeekBar;
import android.widget.ImageView;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.experimental.ExperimentalFeaturesActivity;
import open.cn.awg.pro.experimental.MoreFeaturesActivity;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.settings.DisplayMarginActivity;
import open.cn.awg.pro.settings.LegalInfoActivity;
import open.cn.awg.pro.tools.WearOsToolboxActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;
import open.cn.awg.pro.viewer.TextViewerActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.R;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.NativeDatabase;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.NativeViewValues;
import open.cn.awg.pro.core.JsonValues;
import open.cn.awg.pro.ui.list.NativeListAdapter;
import open.cn.awg.pro.ui.main.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(AndroidJUnit4.class)
public class NativeMigrationSmokeTest {
    private final android.app.Instrumentation instrumentation =
            InstrumentationRegistry.getInstrumentation();
    private final Context targetContext = instrumentation.getTargetContext();

    @Test
    public void applicationAndFileProviderUseNativeAndroidComponents() throws Exception {
        Context application = targetContext.getApplicationContext();
        assertTrue(application instanceof AwgProApplication);
        assertSame(application, AwgProApplication.getInstance());
        assertSame(application, AwgProApplication.getContext());

        PackageInfo packageInfo = targetContext.getPackageManager().getPackageInfo(
                targetContext.getPackageName(), PackageManager.GET_PROVIDERS);
        ProviderInfo provider = null;
        if (packageInfo.providers != null) {
            for (ProviderInfo candidate : packageInfo.providers) {
                if ("open.cn.awg.pro.myFileProvider".equals(candidate.authority)) {
                    provider = candidate;
                    break;
                }
            }
        }
        assertNotNull("FileProvider declaration missing", provider);
        assertEquals(FileProvider.class.getName(), provider.name);
        assertFalse(provider.exported);
        assertTrue(provider.grantUriPermissions);

        File fixture = new File(targetContext.getCacheDir(), "native-provider-smoke.txt");
        try {
            assertTrue(fixture.createNewFile() || fixture.isFile());
            Uri uri = FileProvider.getUriForFile(
                    targetContext, "open.cn.awg.pro.myFileProvider", fixture);
            assertEquals("content", uri.getScheme());
        } finally {
            fixture.delete();
        }
    }

    @Test
    public void nativeFilesResolveReadWriteCopyMissingAndDeleteInFixtureRoots() {
        String fixtureRoot = "native-validation-" + UUID.randomUUID();
        String utf8 = "theme=dark\nuser=腕管Pro ✓";
        String appPath = "$" + fixtureRoot + "/nested/settings.txt";
        String backupPath = "$" + fixtureRoot + "/nested/backup.txt";
        String percentPath = "%" + fixtureRoot + "/percent.txt";
        File externalFiles = targetContext.getExternalFilesDir("");
        assertNotNull("Wear emulator app-specific external storage unavailable", externalFiles);
        File absoluteFile = new File(targetContext.getFilesDir(), fixtureRoot + "/absolute.txt");
        String missingPath = "$" + fixtureRoot + "/missing.txt";

        try {
            assertEquals(new File(targetContext.getFilesDir(), fixtureRoot + "/nested/settings.txt")
                            .getAbsolutePath(), NativeFiles.resolvePath(targetContext, appPath));
            assertEquals(new File(externalFiles, fixtureRoot + "/percent.txt").getAbsolutePath(),
                    NativeFiles.resolvePath(targetContext, percentPath));
            assertEquals(absoluteFile.getAbsolutePath(),
                    NativeFiles.resolvePath(targetContext, absoluteFile.getAbsolutePath()));

            assertTrue(NativeFiles.writeText(targetContext, appPath, utf8));
            assertEquals(utf8, NativeFiles.readText(targetContext, appPath));
            assertTrue(NativeFiles.exists(targetContext, appPath));
            assertTrue(NativeFiles.writeText(targetContext, percentPath, utf8));
            assertEquals(utf8, NativeFiles.readText(targetContext, percentPath));
            assertTrue(NativeFiles.writeText(targetContext, absoluteFile.getAbsolutePath(), utf8));
            assertEquals(utf8, NativeFiles.readText(targetContext, absoluteFile.getAbsolutePath()));

            assertTrue(NativeFiles.exists(targetContext, "@LICENSE"));
            assertNotNull(NativeFiles.readText(targetContext, "@LICENSE"));
            assertFalse(NativeFiles.writeText(targetContext, "@LICENSE", "must not write asset"));
            assertFalse(NativeFiles.exists(targetContext, missingPath));
            assertNull(NativeFiles.readText(targetContext, missingPath));

            assertTrue(NativeFiles.copy(targetContext, appPath, backupPath, false));
            assertEquals(utf8, NativeFiles.readText(targetContext, backupPath));
            assertTrue(NativeFiles.writeText(targetContext, appPath, "theme=light\nuser=腕管Pro ✓"));
            assertEquals(utf8, NativeFiles.readText(targetContext, backupPath));
            assertFalse(NativeFiles.copy(targetContext, appPath, backupPath, false));
            assertTrue(NativeFiles.copy(targetContext, appPath, backupPath, true));
            assertEquals("theme=light\nuser=腕管Pro ✓", NativeFiles.readText(targetContext, backupPath));
            assertTrue(NativeFiles.delete(targetContext, "$" + fixtureRoot, true) > 0);
            assertFalse(NativeFiles.exists(targetContext, appPath));
            assertFalse(NativeFiles.exists(targetContext, absoluteFile.getAbsolutePath()));
            assertTrue(NativeFiles.delete(targetContext, "%" + fixtureRoot, true) > 0);
            assertFalse(NativeFiles.exists(targetContext, percentPath));
        } finally {
            NativeFiles.delete(targetContext, "$" + fixtureRoot, true);
            NativeFiles.delete(targetContext, "%" + fixtureRoot, true);
        }
    }

    @Test
    public void nativeDatabaseReopenPreservesRowsAndSchema() {
        String fixtureRoot = "native-validation-" + UUID.randomUUID();
        String databasePath = "$" + fixtureRoot + "/db/state.db";
        SQLiteDatabase database = null;
        try {
            database = NativeDatabase.open(targetContext, databasePath);
            database.execSQL("CREATE TABLE settings (key TEXT PRIMARY KEY, value TEXT NOT NULL)");
            database.execSQL("CREATE TABLE backup_items (id INTEGER PRIMARY KEY, name TEXT NOT NULL)");
            database.execSQL("INSERT INTO settings (key, value) VALUES (?, ?)",
                    new Object[]{"theme", "dark"});
            database.execSQL("INSERT INTO backup_items (id, name) VALUES (?, ?)",
                    new Object[]{7, "fixture.db"});
            database.setVersion(3);
            database.close();
            database = null;

            database = NativeDatabase.open(targetContext, databasePath);
            assertEquals(3, database.getVersion());
            try (Cursor settings = database.rawQuery(
                    "SELECT key, value FROM settings WHERE key = ?", new String[]{"theme"})) {
                assertTrue(settings.moveToFirst());
                assertEquals("dark", settings.getString(1));
            }
            try (Cursor schema = database.rawQuery(
                    "SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name IN (?, ?)",
                    new String[]{"settings", "backup_items"})) {
                assertTrue(schema.moveToFirst());
                assertEquals(2, schema.getInt(0));
            }
            try (Cursor backup = database.rawQuery(
                    "SELECT name FROM backup_items WHERE id = ?", new String[]{"7"})) {
                assertTrue(backup.moveToFirst());
                assertEquals("fixture.db", backup.getString(0));
            }
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
            NativeFiles.delete(targetContext, "$" + fixtureRoot, true);
        }
    }

    @Test
    public void nativeDatabaseBareNameUsesAppDatabaseDirectory() {
        String name = "native-validation-" + UUID.randomUUID() + ".db";
        String expectedPath = targetContext.getDatabasePath(name).getAbsolutePath();
        SQLiteDatabase database = null;
        String openedPath = null;
        try {
            database = NativeDatabase.open(targetContext, name);
            assertNotNull(database);
            openedPath = database.getPath();
            assertEquals(expectedPath, openedPath);
            database.execSQL("CREATE TABLE fixture (value TEXT NOT NULL)");
            database.execSQL("INSERT INTO fixture (value) VALUES (?)",
                    new Object[]{"bare-name"});
            database.close();
            database = null;

            database = NativeDatabase.open(targetContext, name);
            try (Cursor cursor = database.rawQuery("SELECT value FROM fixture", null)) {
                assertTrue(cursor.moveToFirst());
                assertEquals("bare-name", cursor.getString(0));
            }
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
            if (openedPath != null && !new File(expectedPath).getAbsolutePath()
                    .equals(new File(openedPath).getAbsolutePath())) {
                new File(openedPath).delete();
            }
            targetContext.deleteDatabase(name);
        }
    }

    @Test
    public void nativeListAdapterInflatesNestedRowsAndTracksPositionsAfterUpdates() {
        List<Integer> clickedPositions = new ArrayList<>();
        List<Object> clickedMetadata = new ArrayList<>();
        List<String> clickedText = new ArrayList<>();
        List<String> boundMetadata = new ArrayList<>();
        List<Integer> boundPositions = new ArrayList<>();
        NativeListAdapter[] adapterRef = new NativeListAdapter[1];
        Throwable[] mainThreadFailure = new Throwable[1];

        instrumentation.runOnMainSync(() -> {
            try {
                RecyclerView list = new RecyclerView(targetContext);
                list.setLayoutManager(new LinearLayoutManager(targetContext));
                adapterRef[0] = NativeListAdapter.attach(targetContext, list,
                        R.layout.experimental_feature_item,
                        (itemView, row, position) -> {
                            Object metadata = NativeListAdapter.valueForView(itemView, -41);
                            if (!String.valueOf(row.get(-41)).equals(String.valueOf(metadata))) {
                                throw new AssertionError("Negative-key lookup failed during row bind");
                            }
                            if (!row.equals(NativeListAdapter.rowForView(itemView))) {
                                throw new AssertionError("Bound row lookup failed during row bind");
                            }
                            if (NativeListAdapter.positionForView(itemView) != position) {
                                throw new AssertionError("Bound row position is stale during row bind");
                            }
                            boundMetadata.add(String.valueOf(metadata));
                            boundPositions.add(NativeListAdapter.positionForView(itemView));

                            TextView nestedText = itemView.findViewById(R.id.wb1);
                            nestedText.setOnClickListener(view -> {
                                clickedPositions.add(NativeListAdapter.positionForView(view));
                                clickedMetadata.add(NativeListAdapter.valueForView(view, -41));
                                clickedText.add(String.valueOf(
                                        NativeListAdapter.valueForView(view, R.id.wb1)));
                            });
                        });
                NativeListAdapter adapter = adapterRef[0];
                if (!adapter.addRow(new int[]{R.id.wb1, -41},
                        new Object[]{"first", "first-meta"})
                        || !adapter.addRow(new int[]{R.id.wb1, -41},
                        new Object[]{"second", "second-meta"})) {
                    throw new AssertionError("Could not seed list fixture");
                }
                adapter.refreshRows();
                layoutList(list, targetContext);
                clickNestedText(list, 1);

                if (!adapter.addRow(new int[]{R.id.wb1, -41},
                        new Object[]{"third", "third-meta"})) {
                    throw new AssertionError("Could not append list fixture row");
                }
                adapter.refreshRows();
                layoutList(list, targetContext);
                clickNestedText(list, 1);

                Map<Integer, Object> inserted = row("inserted", "inserted-meta");
                Map<Integer, Object> first = new HashMap<>(adapter.rowAt(0));
                Map<Integer, Object> second = new HashMap<>(adapter.rowAt(1));
                Map<Integer, Object> third = new HashMap<>(adapter.rowAt(2));
                adapter.replaceRows(Arrays.asList(inserted, first, second, third));
                adapter.refreshRows();
                layoutList(list, targetContext);
                clickNestedText(list, 2);

                if (!adapter.removeRow(0)) {
                    throw new AssertionError("Could not remove inserted list fixture row");
                }
                adapter.refreshRows();
                layoutList(list, targetContext);
                clickNestedText(list, 1);
            } catch (Throwable failure) {
                mainThreadFailure[0] = failure;
            }
        });
        instrumentation.waitForIdleSync();

        if (mainThreadFailure[0] != null) {
            throw new AssertionError("Native list instrumentation failed on the main thread",
                    mainThreadFailure[0]);
        }
        assertNotNull(adapterRef[0]);
        assertEquals(4, clickedPositions.size());
        assertEquals(Arrays.asList(1, 1, 2, 1), clickedPositions);
        assertEquals(Arrays.asList("second-meta", "second-meta", "second-meta", "second-meta"),
                clickedMetadata);
        assertEquals(Arrays.asList("second", "second", "second", "second"), clickedText);
        assertTrue(boundMetadata.contains("first-meta"));
        assertTrue(boundMetadata.contains("second-meta"));
        assertTrue(boundMetadata.contains("inserted-meta"));
        assertTrue(boundPositions.containsAll(Arrays.asList(0, 1, 2)));
        assertEquals(3, adapterRef[0].rowCount());
    }

    @Test
    public void launcherActivityStartsAndCanFinish() {
        Intent launchIntent = targetContext.getPackageManager()
                .getLaunchIntentForPackage(targetContext.getPackageName());
        assertNotNull("Launcher activity missing", launchIntent);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Activity activity = instrumentation.startActivitySync(launchIntent);
        instrumentation.waitForIdleSync();
        assertNotNull(activity);
        assertEquals(MainActivity.class, activity.getClass());
        assertFalse(activity.isFinishing());

        instrumentation.runOnMainSync(activity::finish);
        instrumentation.waitForIdleSync();
    }

    @Test
    public void textQrActivityDisplaysTextTitleAndOptionalQrBitmap() {
        Intent textIntent = new Intent(targetContext, TextQrDisplayActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .putExtra("title", "Native fixture")
                .putExtra("text", "UTF-8 腕管Pro text fixture");
        Activity textActivity = instrumentation.startActivitySync(textIntent);
        instrumentation.waitForIdleSync();
        try {
            runOnMainAndAssert(() -> {
                assertEquals("< Native fixture",
                        ((TextView) textActivity.findViewById(R.id.title_bar)).getText().toString());
                assertEquals("UTF-8 腕管Pro text fixture",
                        ((TextView) textActivity.findViewById(R.id.wb1)).getText().toString());
                assertEquals(View.GONE, textActivity.findViewById(R.id.tx1).getVisibility());
            });
        } finally {
            instrumentation.runOnMainSync(textActivity::finish);
        }

        Intent qrIntent = new Intent(targetContext, TextQrDisplayActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .putExtra("title", "QR fixture")
                .putExtra("text", "QR display text")
                .putExtra("QR-Code", "native-validation-qr-code");
        Activity qrActivity = instrumentation.startActivitySync(qrIntent);
        instrumentation.waitForIdleSync();
        try {
            runOnMainAndAssert(() -> {
                ImageView qr = qrActivity.findViewById(R.id.tx1);
                assertEquals("< QR fixture",
                        ((TextView) qrActivity.findViewById(R.id.title_bar)).getText().toString());
                assertEquals("QR display text",
                        ((TextView) qrActivity.findViewById(R.id.wb1)).getText().toString());
                assertEquals(View.VISIBLE, qr.getVisibility());
                assertNotNull("QR image did not produce a bitmap", qr.getDrawable());
            });
        } finally {
            instrumentation.runOnMainSync(qrActivity::finish);
        }
    }

    @Test
    public void displayMarginReadsDefaultPersistsProgressAndSendsRestartMessage() {
        String path = AppPaths.appPath("settings/f17/set1.inf");
        String restartMessagePath = AppPaths.appPath("settings/a4.inf");
        boolean hadOriginal = NativeFiles.exists(targetContext, path);
        String original = NativeFiles.readText(targetContext, path);
        boolean hadRestartMessageFlag = NativeFiles.exists(targetContext, restartMessagePath);
        String originalRestartMessageFlag = NativeFiles.readText(targetContext, restartMessagePath);
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(
                ErrorMessageActivity.class.getName(), null, false);
        Activity marginActivity = null;
        Activity errorActivity = null;
        try {
            assertTrue("Failed to create restart-message test fixture",
                    NativeFiles.writeText(targetContext, restartMessagePath, "true"));
            NativeFiles.delete(targetContext, path, false);
            marginActivity = instrumentation.startActivitySync(new Intent(
                    targetContext, DisplayMarginActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            assertEquals(0, seekBarProgress(marginActivity));
            instrumentation.runOnMainSync(marginActivity::finish);

            assertTrue(NativeFiles.writeText(targetContext, path, "11"));
            marginActivity = instrumentation.startActivitySync(new Intent(
                    targetContext, DisplayMarginActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            assertEquals(11, seekBarProgress(marginActivity));
            DisplayMarginActivity activeMargin = (DisplayMarginActivity) marginActivity;
            runOnMainAndAssert(() -> {
                ((SeekBar) activeMargin.findViewById(R.id.tdt1)).setProgress(23);
                activeMargin.findViewById(R.id.wb1).performClick();
            });
            assertEquals("23", NativeFiles.readText(targetContext, path));

            errorActivity = monitor.waitForActivityWithTimeout(5_000);
            assertNotNull("Applying a changed margin should open the restart message", errorActivity);
            assertEquals("应用成功 点击重启", errorActivity.getIntent().getStringExtra("Msg"));
            instrumentation.runOnMainSync(errorActivity::finish);
            errorActivity = null;
            instrumentation.runOnMainSync(marginActivity::finish);
            marginActivity = null;

            marginActivity = instrumentation.startActivitySync(new Intent(
                    targetContext, DisplayMarginActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            assertEquals("Progress was not restored from app-private storage", 23,
                    seekBarProgress(marginActivity));
        } finally {
            if (errorActivity != null) {
                instrumentation.runOnMainSync(errorActivity::finish);
            }
            if (marginActivity != null) {
                instrumentation.runOnMainSync(marginActivity::finish);
            }
            instrumentation.removeMonitor(monitor);
            if (hadOriginal) {
                NativeFiles.writeText(targetContext, path, original);
            } else {
                NativeFiles.delete(targetContext, path, false);
            }
            if (hadRestartMessageFlag) {
                NativeFiles.writeText(targetContext, restartMessagePath, originalRestartMessageFlag);
            } else {
                NativeFiles.delete(targetContext, restartMessagePath, false);
            }
        }
    }

    @Test
    public void moreFeaturesSupportsDetailAndExperimentalNavigation() {
        MoreFeaturesActivity more = (MoreFeaturesActivity) instrumentation.startActivitySync(
                new Intent(targetContext, MoreFeaturesActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        Instrumentation.ActivityMonitor detailsMonitor = instrumentation.addMonitor(
                TextQrDisplayActivity.class.getName(), null, false);
        Instrumentation.ActivityMonitor experimentalMonitor = null;
        Activity details = null;
        Activity experimental = null;
        try {
            runOnMainAndAssert(() -> assertTrue(
                    more.findViewById(R.id.xdbj4).performLongClick()));
            details = detailsMonitor.waitForActivityWithTimeout(5_000);
            assertNotNull("Long press did not navigate to the local details screen", details);
            assertEquals("详细信息", details.getIntent().getStringExtra("title"));
            assertTrue(details.getIntent().getStringExtra("text").contains("[名称]"));
            instrumentation.runOnMainSync(details::finish);
            details = null;

            experimentalMonitor = instrumentation.addMonitor(
                    ExperimentalFeaturesActivity.class.getName(), null, false);
            instrumentation.runOnMainSync(() -> more.findViewById(R.id.xdbj4).performClick());
            experimental = experimentalMonitor.waitForActivityWithTimeout(5_000);
            assertNotNull("Experimental feature card did not open its native screen", experimental);
            assertTrue(experimental instanceof ExperimentalFeaturesActivity);
        } finally {
            if (details != null) {
                instrumentation.runOnMainSync(details::finish);
            }
            if (experimental != null) {
                instrumentation.runOnMainSync(experimental::finish);
            }
            instrumentation.runOnMainSync(more::finish);
            instrumentation.removeMonitor(detailsMonitor);
            if (experimentalMonitor != null) {
                instrumentation.removeMonitor(experimentalMonitor);
            }
        }
    }

    @Test
    public void legalInfoCardsSendLocalViewerUrlTitleAndCode() {
        LegalInfoActivity legal = (LegalInfoActivity) instrumentation.startActivitySync(
                new Intent(targetContext, LegalInfoActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        int[] cards = {R.id.xdbj14, R.id.xdbj2, R.id.xdbj3};
        String[] paths = {
                AppPaths.appPath("data/assets/b0"),
                AppPaths.appPath("data/assets/b1"),
                AppPaths.appPath("data/assets/b3")
        };
        String[] titles = {"用户协议", "权限使用说明", "腕上微聊公约"};
        String[] fixtures = {
                "Native validation: terms fixture",
                "Native validation: permission fixture",
                "Native validation: chat covenant fixture"
        };
        boolean[] hadOriginal = new boolean[paths.length];
        String[] original = new String[paths.length];
        try {
            for (int index = 0; index < paths.length; index++) {
                hadOriginal[index] = NativeFiles.exists(targetContext, paths[index]);
                original[index] = NativeFiles.readText(targetContext, paths[index]);
                assertTrue("Could not seed legal viewer fixture " + paths[index],
                        NativeFiles.writeText(targetContext, paths[index], fixtures[index]));
            }
            for (int index = 0; index < cards.length; index++) {
                Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(
                        TextViewerActivity.class.getName(), null, false);
                int cardId = cards[index];
                runOnMainAndAssert(() -> legal.findViewById(cardId).performClick());
                Activity viewer = monitor.waitForActivityWithTimeout(5_000);
                try {
                    assertNotNull("Legal information card did not open the text viewer", viewer);
                    assertEquals(paths[index], viewer.getIntent().getStringExtra("url"));
                    assertEquals(titles[index], viewer.getIntent().getStringExtra("title"));
                    assertEquals("super1", viewer.getIntent().getStringExtra("code"));
                } finally {
                    if (viewer != null) {
                        instrumentation.runOnMainSync(viewer::finish);
                    }
                    instrumentation.removeMonitor(monitor);
                }
            }
        } finally {
            instrumentation.runOnMainSync(legal::finish);
            for (int index = 0; index < paths.length; index++) {
                if (hadOriginal[index]) {
                    NativeFiles.writeText(targetContext, paths[index], original[index]);
                } else {
                    NativeFiles.delete(targetContext, paths[index], false);
                }
            }
        }
    }

    @Test
    public void jsonValuesKeepNullEmptyArrayAndObjectSemantics() throws Exception {
        JSONObject object = JsonValues.parseObject(
                "{\"name\":\"腕管Pro\",\"nullable\":null,"
                        + "\"items\":[1,null,3],\"nested\":{\"enabled\":true}}");
        assertNotNull(object);
        assertEquals("腕管Pro", JsonValues.value(object, "name"));
        assertSame(JSONObject.NULL, JsonValues.value(object, "nullable"));
        assertNull(JsonValues.value(object, null));

        JSONArray items = JsonValues.array(object, "items");
        assertNotNull(items);
        assertEquals(3, JsonValues.length(items));
        assertEquals(1, JsonValues.value(items, 0));
        assertSame(JSONObject.NULL, JsonValues.value(items, 1));
        assertEquals(3, JsonValues.value(items, "2"));
        assertNull(JsonValues.value(items, "not-an-index"));

        JSONObject nested = JsonValues.object(object, "nested");
        assertNotNull(nested);
        assertEquals(Boolean.TRUE, JsonValues.value(nested, "enabled"));
        JSONObject objectArray = JsonValues.parseObject(
                "{\"items\":[{\"item\":\"array object\"}]}");
        JSONObject arrayItem = JsonValues.object(JsonValues.array(objectArray, "items"), 0);
        assertEquals("array object", JsonValues.value(arrayItem, "item"));
        assertNull(JsonValues.array(object, "missing"));
        assertNull(JsonValues.object(object, "missing"));
        assertNull(JsonValues.array(items, 0));
        assertNull(JsonValues.object(object, null));

        JSONObject empty = JsonValues.parseObject("{}");
        assertNotNull(empty);
        assertEquals(0, JsonValues.length(empty));
        assertNull(JsonValues.parseObject(null));
        assertNull(JsonValues.parseObject("{\"broken\":"));
        assertEquals(-1, JsonValues.length(null));
        assertEquals(-1, JsonValues.length("not-json-values"));
        assertNull(JsonValues.stringify(null));
        assertNull(JsonValues.stringify("not a JSON object or array"));
        JSONArray roundTrip = new JSONArray(JsonValues.stringify(items));
        assertEquals(3, JsonValues.length(roundTrip));
        assertSame(JSONObject.NULL, JsonValues.value(roundTrip, 1));
        assertEquals("腕管Pro", JsonValues.value(JsonValues.parseObject(
                JsonValues.stringify(object)), "name"));
    }

    @Test
    public void nativeViewValuesPreserveStateSizeTypeAndNamedListPositions() {
        runOnMainAndAssert(() -> {
            TextView text = new TextView(targetContext);
            NativeViewValues.size(text, "20dp", "10dp");
            ViewGroup.LayoutParams size = text.getLayoutParams();
            assertEquals(NativeViewValues.dimension(targetContext, "20dp"), size.width);
            assertEquals(NativeViewValues.dimension(targetContext, "10dp"), size.height);

            NativeViewValues.margins(text, "1dp", "2dp", "3dp", "4dp");
            NativeViewValues.margin(text, 0, "5dp");
            ViewGroup.MarginLayoutParams margins = (ViewGroup.MarginLayoutParams) text.getLayoutParams();
            assertEquals(NativeViewValues.dimension(targetContext, "5dp"), margins.leftMargin);
            assertEquals(NativeViewValues.dimension(targetContext, "2dp"), margins.topMargin);
            assertEquals(NativeViewValues.dimension(targetContext, "3dp"), margins.rightMargin);
            assertEquals(NativeViewValues.dimension(targetContext, "4dp"), margins.bottomMargin);

            NativeViewValues.typeface(text, "monospace");
            assertSame(android.graphics.Typeface.MONOSPACE, text.getTypeface());
            NativeViewValues.background(text, "white");
            assertTrue(text.getBackground() instanceof StateListDrawable);
            StateListDrawable background = (StateListDrawable) text.getBackground();
            background.setState(new int[]{android.R.attr.state_enabled});
            assertEquals(Color.WHITE, ((ColorDrawable) background.getCurrent()).getColor());
            background.setState(new int[]{android.R.attr.state_pressed});
            assertEquals("Pressed but disabled should retain the normal selector state",
                    Color.WHITE, ((ColorDrawable) background.getCurrent()).getColor());
            background.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
            assertEquals(Color.parseColor("#e6eaf7"),
                    ((ColorDrawable) background.getCurrent()).getColor());
            background.setState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled});
            assertEquals(Color.parseColor("#e6eaf7"),
                    ((ColorDrawable) background.getCurrent()).getColor());

            PositionTrackingRecyclerView list = new PositionTrackingRecyclerView(targetContext);
            list.layout(17, 37, 117, 137);
            NativeViewValues.scrollPosition(list, "top");
            assertEquals(list.getTop(), list.lastSmoothScrollPosition);
            NativeViewValues.scrollPosition(list, "bottom");
            assertEquals(list.getBottom(), list.lastSmoothScrollPosition);
            NativeViewValues.scrollPosition(list, "left");
            assertEquals(list.getLeft(), list.lastSmoothScrollPosition);
            NativeViewValues.scrollPosition(list, "right");
            assertEquals(list.getRight(), list.lastSmoothScrollPosition);
            NativeViewValues.scrollPosition(list, 9);
            assertEquals(9, list.lastSmoothScrollPosition);
        });
    }

    @Test
    public void experimentalAndWearToolboxListsKeepExpectedRowsAndBindings() {
        String displayModePath = AppPaths.appPath("settings/a3.inf");
        boolean hadOriginal = NativeFiles.exists(targetContext, displayModePath);
        String original = NativeFiles.readText(targetContext, displayModePath);
        Activity activity = null;
        try {
            NativeFiles.delete(targetContext, displayModePath, false);
            activity = instrumentation.startActivitySync(new Intent(
                    targetContext, ExperimentalFeaturesActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            instrumentation.waitForIdleSync();
            ExperimentalFeaturesActivity experimental = (ExperimentalFeaturesActivity) activity;
            assertEquals(2, experimental.spq.rowCount());
            assertEquals("服务端调试", experimental.spq.valueAt(0, R.id.wb1));
            assertEquals("Shizuku命令行", experimental.spq.valueAt(1, R.id.wb1));
            assertFirstBoundRow(experimental, "服务端调试");
            instrumentation.runOnMainSync(experimental::finish);
            activity = null;

            assertTrue(NativeFiles.writeText(targetContext, displayModePath, "4"));
            activity = instrumentation.startActivitySync(new Intent(
                    targetContext, ExperimentalFeaturesActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            ExperimentalFeaturesActivity modeFour = (ExperimentalFeaturesActivity) activity;
            assertEquals(3, modeFour.spq.rowCount());
            assertEquals("指纹验证", modeFour.spq.valueAt(2, R.id.wb1));
            instrumentation.runOnMainSync(modeFour::finish);
            activity = null;

            activity = instrumentation.startActivitySync(new Intent(
                    targetContext, WearOsToolboxActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            WearOsToolboxActivity toolbox = (WearOsToolboxActivity) activity;
            assertEquals(6, toolbox.spq.rowCount());
            assertEquals(Arrays.asList("开启蓝牙", "eSIM状态", "eSIM重置", "账号",
                            "Wear云同步", "Google账号"),
                    Arrays.asList(
                            toolbox.spq.valueAt(0, R.id.wb1), toolbox.spq.valueAt(1, R.id.wb1),
                            toolbox.spq.valueAt(2, R.id.wb1), toolbox.spq.valueAt(3, R.id.wb1),
                            toolbox.spq.valueAt(4, R.id.wb1), toolbox.spq.valueAt(5, R.id.wb1)));
            assertEquals("com.google.android.apps.wearable.settings",
                    toolbox.spq.valueAt(0, -1));
            assertEquals("com.google.android.clockwork.settings.connectivity.bluetooth.BluetoothRequestDiscoverableActivity",
                    toolbox.spq.valueAt(0, -2));
            instrumentation.waitForIdleSync();
            assertFirstBoundRow(toolbox, "开启蓝牙");
        } finally {
            if (activity != null) {
                Activity finalActivity = activity;
                instrumentation.runOnMainSync(finalActivity::finish);
            }
            if (hadOriginal) {
                NativeFiles.writeText(targetContext, displayModePath, original);
            } else {
                NativeFiles.delete(targetContext, displayModePath, false);
            }
        }
    }

    @Test
    public void freshInstallKeepsLocationPermissionDenied() {
        assertEquals(PackageManager.PERMISSION_DENIED,
                targetContext.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION));
        assertEquals(PackageManager.PERMISSION_DENIED,
                targetContext.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    private int seekBarProgress(Activity activity) {
        int[] progress = new int[1];
        runOnMainAndAssert(() -> progress[0] =
                ((SeekBar) activity.findViewById(R.id.tdt1)).getProgress());
        return progress[0];
    }

    private void assertFirstBoundRow(Activity activity, String expectedText) {
        runOnMainAndAssert(() -> {
            RecyclerView list = activity.findViewById(R.id.v7lb1);
            RecyclerView.ViewHolder holder = list.findViewHolderForAdapterPosition(0);
            assertNotNull("First list row was not inflated", holder);
            TextView title = holder.itemView.findViewById(R.id.wb1);
            assertNotNull("First list title was not inflated", title);
            assertEquals(expectedText, title.getText().toString());
        });
    }

    private interface UiAssertion {
        void run() throws Throwable;
    }

    private void runOnMainAndAssert(UiAssertion assertion) {
        Throwable[] failure = new Throwable[1];
        instrumentation.runOnMainSync(() -> {
            try {
                assertion.run();
            } catch (Throwable error) {
                failure[0] = error;
            }
        });
        if (failure[0] != null) {
            throw new AssertionError("UI instrumentation assertion failed", failure[0]);
        }
    }

    private static final class PositionTrackingRecyclerView extends RecyclerView {
        int lastSmoothScrollPosition = Integer.MIN_VALUE;

        PositionTrackingRecyclerView(Context context) {
            super(context);
        }

        @Override
        public void smoothScrollToPosition(int position) {
            lastSmoothScrollPosition = position;
        }
    }

    private static void layoutList(RecyclerView list, Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        list.measure(widthSpec, heightSpec);
        list.layout(0, 0, width, height);
    }

    private static void clickNestedText(RecyclerView list, int position) {
        RecyclerView.ViewHolder holder = list.findViewHolderForAdapterPosition(position);
        if (holder == null) {
            throw new AssertionError("No inflated row at adapter position " + position);
        }
        View nestedText = holder.itemView.findViewById(R.id.wb1);
        if (nestedText == null) {
            throw new AssertionError("Nested row TextView was not inflated");
        }
        nestedText.performClick();
    }

    private static Map<Integer, Object> row(String text, String metadata) {
        Map<Integer, Object> row = new HashMap<>();
        row.put(R.id.wb1, text);
        row.put(-41, metadata);
        return row;
    }
}