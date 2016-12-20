package com.tisen.titans.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tisen.titans.R;
import com.tisen.titans.api.NewsServes;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tisen on 2016/12/19 17:41.
 * Email tisences@qq.com
 */
public class NewsActivity extends BaseActivity {
    private NewsFragment newsFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.news);

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NewsFragment.API)
                .build();
        NewsServes serves = retrofit.create(NewsServes.class);

        newsFragment.setType("top",serves);
    }
}
