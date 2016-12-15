package com.tisen.titans.utils;

import android.util.Log;

import com.tisen.titans.BuildConfig;

/**
 * Log工具类
 * Created by tisen on 2016/12/14 17:44.
 * Email tisences@qq.com
 */
public class LogUtil {
    private static boolean debug = BuildConfig.DEBUG;
    private static String separator = ",";
    private static boolean showInfo = true;

    private LogUtil(){
        throw new UnsupportedOperationException("LogUtil cannot be instantiated");
    }


    public static void setDebug(boolean debug) {
        LogUtil.debug = debug;
    }

    public static void setSeparator(String separator) {
        LogUtil.separator = separator;
    }

    public static void setShowInfo(boolean showInfo) {
        LogUtil.showInfo = showInfo;
    }

    public static void v(String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.v(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void v(String tag, String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtil.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.v(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void d(String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.d(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void d(String tag, String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtil.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.d(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void i(String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.i(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void i(String tag, String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtil.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.i(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void w(String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.w(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void w(String tag, String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtil.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.w(tag, message + getLogInfo(stackTraceElement));
        }
    }

    public static void e(String tag, String message) {
        if (debug) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtil.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.e(tag, message + getLogInfo(stackTraceElement));
        }
    }

    private static String getDefaultTag(StackTraceElement stackTraceElement) {
        return stackTraceElement.getFileName().split("\\.")[0];
    }

    private static String getLogInfo(StackTraceElement stackTraceElement) {
        if (!showInfo) return "";
        StringBuilder logInfo = new StringBuilder();
        String threadName = Thread.currentThread().getName();
        long threadID = Thread.currentThread().getId();
        String fileName = stackTraceElement.getFileName();
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();

        logInfo.append(" ===>>[");
        logInfo.append("thread name = " + threadName).append(separator);
//        logInfo.append("thread ID = " + threadID).append(separator);
//        logInfo.append("file name = " + fileName).append(separator);
//        logInfo.append("class name = " + className).append(separator);
        logInfo.append("method name = " + methodName).append(separator);
        logInfo.append("line number = " + lineNumber).append("]");

        return logInfo.toString();
    }

}
