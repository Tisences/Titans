package com.tisen.titans.utils;

import android.content.Context;
import android.util.TypedValue;

/**单位转换工具类
 * Created by tisen on 2016/12/14 19:49.
 * Email tisences@qq.com
 */
public class DensityUtil {
    private DensityUtil() {
        throw new UnsupportedOperationException("DensityUtil cannot be instantiated");
    }
    public static int dp2px(Context context,float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,context.getResources().getDisplayMetrics());
    }
    public static int sp2px(Context context,float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,context.getResources().getDisplayMetrics());
    }
    public static float px2dp(Context context,float pxVal){
        return (pxVal/context.getResources().getDisplayMetrics().density);
    }
    public static float px2sp(Context context,float pxVal){
        return (pxVal/context.getResources().getDisplayMetrics().scaledDensity);
    }
}
