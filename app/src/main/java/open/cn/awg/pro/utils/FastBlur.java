package open.cn.awg.pro.utils;

import android.graphics.Bitmap;

/**
 * 纯 Java 快速模糊（三次方框模糊近似高斯），不依赖 RenderEffect / RenderScript，
 * API 22+ 均可用。设计用于很小的位图（降采样后），性能开销极低。
 */
public final class FastBlur {

    private FastBlur() { }

    public static void blur(Bitmap bmp, int radius) {
        if (bmp == null || radius < 1) return;
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        if (w < 2 || h < 2) return;
        int[] pix = new int[w * h];
        bmp.getPixels(pix, 0, w, 0, 0, w, h);
        int[] tmp = new int[w * h];
        for (int pass = 0; pass < 3; pass++) {
            boxH(pix, tmp, w, h, radius);
            boxV(tmp, pix, w, h, radius);
        }
        bmp.setPixels(pix, 0, w, 0, 0, w, h);
    }

    private static int clamp(int v, int max) {
        return v < 0 ? 0 : (v > max ? max : v);
    }

    private static void boxH(int[] in, int[] out, int w, int h, int r) {
        int div = r * 2 + 1;
        for (int y = 0; y < h; y++) {
            int row = y * w;
            int sa = 0, sr = 0, sg = 0, sb = 0;
            for (int i = -r; i <= r; i++) {
                int c = in[row + clamp(i, w - 1)];
                sa += (c >>> 24) & 0xff; sr += (c >> 16) & 0xff;
                sg += (c >> 8) & 0xff; sb += c & 0xff;
            }
            for (int x = 0; x < w; x++) {
                out[row + x] = ((sa / div) << 24) | ((sr / div) << 16) | ((sg / div) << 8) | (sb / div);
                int cAdd = in[row + clamp(x + r + 1, w - 1)];
                int cSub = in[row + clamp(x - r, w - 1)];
                sa += ((cAdd >>> 24) & 0xff) - ((cSub >>> 24) & 0xff);
                sr += ((cAdd >> 16) & 0xff) - ((cSub >> 16) & 0xff);
                sg += ((cAdd >> 8) & 0xff) - ((cSub >> 8) & 0xff);
                sb += (cAdd & 0xff) - (cSub & 0xff);
            }
        }
    }

    private static void boxV(int[] in, int[] out, int w, int h, int r) {
        int div = r * 2 + 1;
        for (int x = 0; x < w; x++) {
            int sa = 0, sr = 0, sg = 0, sb = 0;
            for (int i = -r; i <= r; i++) {
                int c = in[clamp(i, h - 1) * w + x];
                sa += (c >>> 24) & 0xff; sr += (c >> 16) & 0xff;
                sg += (c >> 8) & 0xff; sb += c & 0xff;
            }
            for (int y = 0; y < h; y++) {
                out[y * w + x] = ((sa / div) << 24) | ((sr / div) << 16) | ((sg / div) << 8) | (sb / div);
                int cAdd = in[clamp(y + r + 1, h - 1) * w + x];
                int cSub = in[clamp(y - r, h - 1) * w + x];
                sa += ((cAdd >>> 24) & 0xff) - ((cSub >>> 24) & 0xff);
                sr += ((cAdd >> 16) & 0xff) - ((cSub >> 16) & 0xff);
                sg += ((cAdd >> 8) & 0xff) - ((cSub >> 8) & 0xff);
                sb += (cAdd & 0xff) - (cSub & 0xff);
            }
        }
    }
}
