package com.tisen.titans.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tisen.titans.R;
import com.tisen.titans.api.NewsServes;
import com.tisen.titans.ui.*;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by tisen on 2017/9/13.
 */

public class ServiceActivity extends AppCompatActivity {
    private ControlFragment controlFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        controlFragment = (ControlFragment) getSupportFragmentManager().findFragmentById(R.id.control);
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ControlFragment.API)
                .build();
        ControlService serves = retrofit.create(ControlService.class);

        controlFragment.setService(serves);
    }
}
