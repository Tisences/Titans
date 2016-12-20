package com.tisen.titans.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tisen.titans.R;
import com.tisen.titans.api.SmileResult;
import com.tisen.titans.api.SmileServes;
import com.tisen.titans.utils.LogUtil;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tisen on 2016/12/16 15:14.
 * Email tisences@qq.com
 */
public class SmileActivity extends BaseActivity {

    public static final String API = "http://japi.juhe.cn/joke/content/";
    public static final String KEY = "847f7c2568b72f239d07559330f4749d";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API)
                .build();
        SmileServes smileServes = retrofit.create(SmileServes.class);
        smileServes.getSmile(1,20,KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<SmileResult, Boolean>() {
                    @Override
                    public Boolean call(SmileResult smileResult) {
                        return smileResult.getReason().equals("Success");
                    }
                })
                .map(new Func1<SmileResult, List<SmileResult.ResultBean.DataBean>>() {
                    @Override
                    public List<SmileResult.ResultBean.DataBean> call(SmileResult smileResult) {
                        return smileResult.getResult().getData();
                    }
                }).flatMap(new Func1<List<SmileResult.ResultBean.DataBean>, Observable<SmileResult.ResultBean.DataBean>>() {
            @Override
            public Observable<SmileResult.ResultBean.DataBean> call(List<SmileResult.ResultBean.DataBean> dataBeen) {
                return Observable.from(dataBeen);
            }
        }).subscribe(new Action1<SmileResult.ResultBean.DataBean>() {
            @Override
            public void call(SmileResult.ResultBean.DataBean dataBean) {
                LogUtil.i(dataBean.toString());
            }
        });
    }
}
