package com.tisen.titans.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**网络工具类
 * Created by tisen on 2016/12/14 20:54.
 * Email tisences@qq.com
 */
public class NetUtil {
    private NetUtil(){
        throw new UnsupportedOperationException("NetUtil cannot be instantiated");
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info!=null&&info.isConnected()){
                if(info.getState()== NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager==null)
            return false;
        return connectivityManager.getActiveNetworkInfo().getType()==ConnectivityManager.TYPE_WIFI;
    }
    public static void openWirelessSetting(Activity activity){
        Intent intent = new Intent("/");
        ComponentName name = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
        intent.setComponent(name);
        activity.startActivityForResult(intent,0);
    }

}
