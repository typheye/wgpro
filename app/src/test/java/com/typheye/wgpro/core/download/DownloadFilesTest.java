package com.typheye.wgpro.core.download;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DownloadFilesTest {

    @Rule
    public final TemporaryFolder temporary = new TemporaryFolder();

    @Test
    public void commitUsesContentSha256WithoutExtension() throws Exception {
        File partial = temporary.newFile("partial");
        try (FileOutputStream out = new FileOutputStream(partial)) {
            out.write("wgpro".getBytes(StandardCharsets.UTF_8));
        }

        File managed = temporary.newFolder("downloads");
        String digest = DownloadFiles.sha256(partial);
        File result = DownloadFiles.commit(partial, managed);

        assertEquals(digest, result.getName());
        assertEquals(digest, DownloadFiles.sha256(result));
        assertEquals(64, result.getName().length());
        assertTrue(result.isFile());
        assertFalse(partial.exists());
        assertFalse(result.getName().contains("."));
    }

    @Test
    public void validatesOnlyLowercaseSha256Names() {
        assertTrue(DownloadFiles.isSha256Name(
                "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"));
        assertFalse(DownloadFiles.isSha256Name("0123456789ABCDEF"));
        assertFalse(DownloadFiles.isSha256Name("download.apk"));
    }
}
