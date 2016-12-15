package com.tisen.titans.utils;

import android.os.Environment;

import java.io.File;

/**SD卡工具类
 * Created by tisen on 2016/12/14 20:00.
 * Email tisences@qq.com
 */
public class SDCardUtil {
    private SDCardUtil() {
        throw new UnsupportedOperationException("SDCardUtil cannot be instantiated");
    }
    public static boolean isSDCardEnable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static String getSDCardPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }
    public static String getRootDirectoryPath(){
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
