package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import open.cn.awg.pro.service.ShizukuShellService;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class NativeShizukuServiceTest {
    @Test
    public void executeCapturesStdoutAndStderrLinesFromLocalShell() throws Exception {
        ShizukuShellService service = new ShizukuShellService();
        String output = service.execute(
                "printf '%s\\n' 'native_fixture'; printf '%s\\n' 'stderr_fixture' >&2");

        assertEquals("native_fixture\nstderr_fixture\n", output);
    }
}
