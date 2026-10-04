package open.cn.awg.pro.service;

import android.os.RemoteException;
import androidx.annotation.Keep;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** Runs in Shizuku's separate user-service process. */
@Keep
public final class ShizukuShellService extends IShizukuShell.Stub {
    @Keep
    public ShizukuShellService() { }

    @Override
    public void destroy() { System.exit(0); }

    @Override
    public String execute(String command) throws RemoteException {
        try {
            Process process = new ProcessBuilder("/system/bin/sh", "-c", command)
                    .redirectErrorStream(true).start();
            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) result.append(line).append('\n');
            }
            process.waitFor();
            return result.toString();
        } catch (Exception failure) {
            if (failure instanceof InterruptedException) Thread.currentThread().interrupt();
            throw new RemoteException(failure.toString());
        }
    }
}
