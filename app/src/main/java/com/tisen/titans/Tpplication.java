package com.tisen.titans;

import android.app.Application;

import com.tisen.titans.utils.LogUtil;
import com.tisen.titans.utils.SPUtil;
import com.tisen.titans.utils.ScreenUtil;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by tisen on 2016/12/14 18:26.
 * Email tisences@qq.com
 */
public class Tpplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.setDebug(true);
        LogUtil.setShowInfo(true);
        LogUtil.setSeparator("]--[");
        SPUtil.setSPName("titans");

        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
