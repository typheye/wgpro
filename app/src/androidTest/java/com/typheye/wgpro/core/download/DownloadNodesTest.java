package com.typheye.wgpro.core.download;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DownloadNodesTest {

    @Test
    public void shanghaiNodeIsBlockedByIdAndHost() {
        assertTrue(DownloadNodes.isNodeDisabled("dl3", null));
        assertTrue(DownloadNodes.isNodeDisabled(
                null, "https://dl.otodone.com/speedtest.bin"));
        assertTrue(DownloadNodes.isDisabledMirrorUrl(
                "https://res.typheye.cn/api.php?action=dl&node=dl3"));
    }

    @Test
    public void hongKongNodesRemainAvailable() {
        assertFalse(DownloadNodes.isNodeDisabled(
                "dl", "https://dl.typheye.cn/speedtest.bin"));
        assertFalse(DownloadNodes.isNodeDisabled(
                "dl2", "https://dl2.otodone.com/speedtest.bin"));
    }
}
