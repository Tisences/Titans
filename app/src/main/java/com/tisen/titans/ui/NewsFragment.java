package com.tisen.titans.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tisen.titans.R;
import com.tisen.titans.api.NewsResult;
import com.tisen.titans.api.NewsServes;
import com.tisen.titans.utils.DensityUtil;
import com.tisen.titans.utils.LogUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tisen on 2016/12/19 16:35.
 * Email tisences@qq.com
 */
@ContentView(R.layout.fragment_new)
public class NewsFragment extends Fragment {
    @ViewInject(R.id.refreshView)
    protected PtrFrameLayout refreshView;
    @ViewInject(R.id.recycleView)
    protected RecyclerView recyclerView;
    private String type;
    private NewsServes serves;
    private List<NewsResult.ResultBean.DataBean> results = new ArrayList<>();
    private NewsAdapter adapter;

    public static final String API = "http://v.juhe.cn/toutiao/";
    public static final String KEY = "09fb4a737db6fce6c8a1036844ab39f6";

    protected long time;
    protected boolean isPrepared;
    protected boolean isVisible;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewsAdapter(results, getActivity());
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, NewsResult.ResultBean.DataBean dataBean) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url",dataBean.getUrl());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        isPrepared = true;
        refreshView.setResistance(1.7f);
        refreshView.setRatioOfHeaderHeightToRefresh(1.2f);
        refreshView.setDurationToClose(200);
        refreshView.setDurationToCloseHeader(1000);
        refreshView.setPullToRefresh(false);
        refreshView.setKeepHeaderWhenRefresh(true);
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("NEWS");
        header.initWithStringArray(R.array.storehouse);
        refreshView.setHeaderView(header);
        refreshView.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                reFresh();
            }
        });
        return view;
    }

    public void setType(String type, NewsServes serves) {
        this.type = type;
        this.serves = serves;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        if (System.currentTimeMillis() - time <= 1000 * 300) {
            return;
        }
        if (refreshView != null)
            refreshView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshView.autoRefresh(true);
                }
            },150);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    private void reFresh() {
        LogUtil.i("refresh");
        serves.getNews(KEY, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {
            @Override
            public void onCompleted() {
                LogUtil.i("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(NewsResult newsResult) {
                refreshView.refreshComplete();
                adapter.setResults(newsResult.getResult().getData());
            }
        });
    }
}
