package com.tisen.titans;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.smtt.sdk.QbSdk;
import com.tisen.titans.utils.LogUtil;
import com.tisen.titans.utils.SPUtil;
import com.tisen.titans.utils.ScreenUtil;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * Created by tisen on 2016/12/14 18:26.
 * Email tisences@qq.com
 */
public class Tpplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        LogUtil.setDebug(true);
        LogUtil.setShowInfo(true);
        QbSdk.initX5Environment(this,null);
        LogUtil.setSeparator("]--[");
        SPUtil.setSPName("titans");
        Fresco.initialize(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
