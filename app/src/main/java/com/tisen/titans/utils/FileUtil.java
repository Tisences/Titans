package com.tisen.titans.utils;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**文件存储工具类
 * Created by tisen on 2016/12/14 21:09.
 * Email tisences@qq.com
 */
public class FileUtil {
    public static File saveBitmap(Bitmap bitmap,String path,String fileName){
        File tmpDir = new File(SDCardUtil.getSDCardPath()+path);
        if(!tmpDir.exists()){
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath()+fileName);
        if(img.exists()){
            img.delete();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
