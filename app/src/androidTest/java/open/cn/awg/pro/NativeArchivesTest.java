package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.core.NativeArchives;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@RunWith(AndroidJUnit4.class)
public final class NativeArchivesTest {
    private final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void createAndExtractPreserveCountsOverwriteAndPrefixFiltering() throws Exception {
        File fixture = fixtureDirectory();
        File source = new File(fixture, "source");
        File nestedFile = new File(source, "nested/data.txt");
        File emptyDirectory = new File(source, "empty-directory");
        File archive = new File(fixture, "fixture.zip");
        File destination = new File(fixture, "destination");
        File prefixDestination = new File(fixture, "prefix-destination");
        try {
            assertTrue(nestedFile.getParentFile().mkdirs());
            assertTrue(emptyDirectory.mkdirs());
            writeText(nestedFile, "fixture contents");

            assertEquals(2, NativeArchives.create(context, source.getAbsolutePath(), archive.getAbsolutePath()));
            try (ZipFile zip = new ZipFile(archive)) {
                Set<String> entries = new HashSet<>();
                java.util.Enumeration<? extends ZipEntry> enumeration = zip.entries();
                while (enumeration.hasMoreElements()) entries.add(enumeration.nextElement().getName());
                assertEquals(new HashSet<>(Arrays.asList("nested/data.txt", "empty-directory/")), entries);
                assertEquals("fixture contents", readText(zip.getInputStream(zip.getEntry("nested/data.txt"))));
            }

            assertEquals(2, NativeArchives.extract(context, archive.getAbsolutePath(),
                    destination.getAbsolutePath(), false));
            File extractedFile = new File(destination, "nested/data.txt");
            assertEquals("fixture contents", readText(extractedFile));

            writeText(extractedFile, "keep existing");
            assertEquals(2, NativeArchives.extract(context, archive.getAbsolutePath(),
                    destination.getAbsolutePath(), false));
            assertEquals("keep existing", readText(extractedFile));
            assertEquals(2, NativeArchives.extract(context, archive.getAbsolutePath(),
                    destination.getAbsolutePath(), true));
            assertEquals("fixture contents", readText(extractedFile));

            assertEquals(1, NativeArchives.extract(context, archive.getAbsolutePath(), "nested/",
                    prefixDestination.getAbsolutePath(), false));
            assertEquals("fixture contents", readText(new File(prefixDestination, "nested/data.txt")));
            assertFalse(new File(prefixDestination, "empty-directory").exists());
        } finally {
            deleteRecursively(fixture);
        }
    }

    @Test
    public void extractRejectsTraversalEntriesOutsideDestination() throws Exception {
        File fixture = fixtureDirectory();
        assertTrue("fixture directory should be created", fixture.mkdirs());
        File archive = new File(fixture, "traversal.zip");
        File destination = new File(fixture, "safe-destination");
        File escaped = new File(fixture, "escaped.txt");
        try {
            try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(archive))) {
                output.putNextEntry(new ZipEntry("../escaped.txt"));
                output.write("outside".getBytes(StandardCharsets.UTF_8));
                output.closeEntry();
            }

            assertEquals(1, NativeArchives.extract(context, archive.getAbsolutePath(),
                    destination.getAbsolutePath(), true));
            assertFalse("ZIP traversal must not write beside the destination", escaped.exists());
            assertFalse("An archive with only rejected entries need not create the destination",
                    destination.exists());
        } finally {
            deleteRecursively(fixture);
        }
    }

    private File fixtureDirectory() {
        return new File(context.getCacheDir(), "native-archives-" + UUID.randomUUID());
    }

    private static void writeText(File file, String value) throws Exception {
        File parent = file.getParentFile();
        if (parent != null) parent.mkdirs();
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(value.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static String readText(File file) throws Exception {
        try (FileInputStream input = new FileInputStream(file)) {
            return readText(input);
        }
    }

    private static String readText(InputStream input) throws Exception {
        try (InputStream source = input; ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int count;
            while ((count = source.read(buffer)) != -1) output.write(buffer, 0, count);
            return output.toString(StandardCharsets.UTF_8.name());
        }
    }

    private static void deleteRecursively(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) deleteRecursively(child);
        }
        file.delete();
    }
}
