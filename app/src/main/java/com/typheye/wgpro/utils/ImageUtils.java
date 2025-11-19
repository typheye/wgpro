package com.typheye.wgpro.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class ImageUtils {

    /**
     * 将任意尺寸的图片转换为圆形头像
     * 步骤：
     * 1. 取宽高中较小值作为正方形边长
     * 2. 从原图中心裁剪出最大正方形区域
     * 3. 使用 PorterDuff.Mode.SRC_IN 绘制圆形遮罩
     *
     * @param bitmap 原始图片
     * @return 圆形头像 Bitmap
     */
    public static Bitmap roundBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // 正方形边长取宽高中较小者
        int size = Math.min(width, height);

        // 计算从原图裁剪的起始坐标（居中裁剪）
        int left, top, right, bottom;
        if (width > height) {
            // 宽 > 高：横向居中，裁剪左右
            left = (width - height) / 2;
            top = 0;
            right = left + height;
            bottom = height;
        } else {
            // 高 >= 宽：纵向居中，裁剪上下
            top = (height - width) / 2;
            left = 0;
            bottom = top + width;
            right = width;
        }

        // 裁剪出中心正方形
        Bitmap squaredBitmap = Bitmap.createBitmap(bitmap, left, top, right - left, bottom - top);

        // 创建输出的圆形 Bitmap
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        float radius = size / 2f;

        // 先画一个圆（作为遮罩区域）
        canvas.drawCircle(radius, radius, radius, paint);

        // 设置遮罩模式：只保留源图像在目标圆内的部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // 将裁剪后的正方形图绘制到圆形区域
        canvas.drawBitmap(squaredBitmap, 0, 0, paint);

        // 回收临时裁剪图（避免内存泄漏，注意：原图不要在这里回收）
        if (squaredBitmap != bitmap) {
            squaredBitmap.recycle();
        }

        return output;
    }

    public static Bitmap createQRCodeBitmap(String content, int width,int height,
                                            String character_set,String error_correction_level,
                                            String margin, int colorWhite, int colorBlack) {
//        int color_black= Color.BLACK;
//        int color_white=Color.WHITE;
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /* 1.设置二维码相关配置 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /* 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /* 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = colorBlack;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = colorWhite;// 白色色块像素设置
                    }
                }
            }
            /* 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}