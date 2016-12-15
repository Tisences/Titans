package com.tisen.titans.utils;

import android.content.Context;
import android.widget.Toast;

/**Toast工具类
 * Created by tisen on 2016/12/14 18:51.
 * Email tisences@qq.com
 */
public class ToastUtil {
    private static Toast toast;
    private ToastUtil(){
        throw new UnsupportedOperationException("ToastUtil cannot be instantiated");
    }
    public static void showShort(Context context,CharSequence sequence){
        if(toast == null){
            toast = Toast.makeText(context,sequence,Toast.LENGTH_SHORT);
        }else {
            toast.setText(sequence);
        }
        toast.show();
    }

    public static void showShort(Context context,int message){
        if(toast == null){
            toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showLong(Context context,CharSequence sequence){
        if(toast == null){
            toast = Toast.makeText(context,sequence,Toast.LENGTH_LONG);
        }else {
            toast.setText(sequence);
        }
        toast.show();
    }

    public static void showLong(Context context,int message){
        if(toast == null){
            toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        }else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void show(Context context,CharSequence sequence,int duration){
        if(toast == null){
            toast = Toast.makeText(context,sequence,duration);
        }else {
            toast.setText(sequence);
        }
        toast.show();
    }
    public static void hide(){
        if(toast!=null){
            toast.cancel();
        }
    }

}
