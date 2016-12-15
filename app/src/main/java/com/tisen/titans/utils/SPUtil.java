package com.tisen.titans.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**SharedPreferences工具类
 * Created by tisen on 2016/12/14 19:18.
 * Email tisences@qq.com
 */
public class SPUtil {
    private static String sp_name = "share_data";

    private SPUtil(){
        throw new UnsupportedOperationException("SPUtil cannot be instantiated");
    }

    public static void setSPName(String sp_name){
        SPUtil.sp_name = sp_name;
    }

    public static void put(Context context,String key,Object object){
        SharedPreferences.Editor editor = getEditor(context);
        if(object instanceof String){
            editor.putString(key, (String) object);
        }else if(object instanceof Integer){
            editor.putInt(key, (Integer) object);
        }else if(object instanceof Boolean){
            editor.putBoolean(key, (Boolean) object);
        }else if(object instanceof Float){
            editor.putFloat(key, (Float) object);
        }else if(object instanceof Long){
            editor.putLong(key, (Long) object);
        }else {
            editor.putString(key,object.toString());
        }
        editor.apply();
    }

    public static Object get(Context context,String key,Object defaultObject){
        SharedPreferences preferences = getPreferences(context);

        if(defaultObject instanceof String){
            return preferences.getString(key, (String) defaultObject);
        }else if(defaultObject instanceof Integer){
            return preferences.getInt(key, (Integer) defaultObject);
        }else if(defaultObject instanceof Boolean){
            return preferences.getBoolean(key, (Boolean) defaultObject);
        }else if(defaultObject instanceof Float){
            return preferences.getFloat(key, (Float) defaultObject);
        }else if(defaultObject instanceof Long){
            return preferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public static void remove(Context context,String key){
        getEditor(context).remove(key).apply();
    }

    public static void clear(Context context){
        getEditor(context).clear().apply();
    }
    public static boolean contains(Context context,String key){
        return getPreferences(context).contains(key);
    }
    public static Map<String,?>getAll(Context context){
        return getPreferences(context).getAll();
    }

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(sp_name,Context.MODE_PRIVATE);
    }
    private static SharedPreferences.Editor getEditor(Context context){
        return getPreferences(context).edit();
    }
}
