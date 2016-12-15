package com.tisen.titans.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**屏幕工具类
 * Created by tisen on 2016/12/14 20:38.
 * Email tisences@qq.com
 */
public class ScreenUtil {
    private ScreenUtil(){
        throw new UnsupportedOperationException("ScreenUtil cannot be instantiated");
    }

    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return  metrics.widthPixels;
    }

    public static int getScreenHeight(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getStatusHeight(Context context){
        int statusHeight = -1;
        try {
            Class<?>clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
