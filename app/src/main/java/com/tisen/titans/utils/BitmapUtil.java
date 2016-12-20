package com.tisen.titans.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Target;

/**Bitmap工具类
 * Created by tisen on 2016/12/14 21:08.
 * Email tisences@qq.com
 */
public class BitmapUtil {

    private static int defaultColor = 0x000000;
    /**
     * 模糊一张图片
     * @param context
     * @param inputBitmap
     * @param radius
     * @return
     */
    public static Bitmap blurBitmap(Context context ,Bitmap inputBitmap,int radius){
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(),inputBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript script = RenderScript.create(context);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(script, Element.U8_4(script));

        Allocation allIn = Allocation.createFromBitmap(script,inputBitmap);
        Allocation allOut = Allocation.createFromBitmap(script,outputBitmap);

        blur.setRadius(radius);
        blur.setInput(allIn);
        blur.forEach(allOut);

        allOut.copyTo(outputBitmap);
        inputBitmap.recycle();
        script.destroy();
        return  outputBitmap;
    }

    /**
     * 获取图片主色调
     * 同步方法
     * @param bitmap
     * @param target
     * @return
     */

    public static int getPaletteColor(Bitmap bitmap, Target target){
        Palette palette = Palette.from(bitmap).generate();
        return palette.getColorForTarget(target,defaultColor);
    }

    /**
     * 获取图片主色调
     * 异步方法(防止图片过大时，阻塞主线程)
     * @param bitmap
     * @param target
     * @param callBack
     */
    public static void getPaletteColor(Bitmap bitmap, final Target target, final PaletteColorCallBack callBack){
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                callBack.onComplete(palette.getColorForTarget(target,defaultColor));
            }
        });
    }
    public interface PaletteColorCallBack{
        void onComplete(int color);
    }
}
