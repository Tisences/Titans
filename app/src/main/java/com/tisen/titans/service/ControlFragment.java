package com.tisen.titans.service;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.tisen.titans.R;
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
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tisen on 2016/12/19 16:35.
 * Email tisences@qq.com
 */
@ContentView(R.layout.fragment_new)
public class ControlFragment extends Fragment implements ControlAdapter.OnItemClickListener {
    @ViewInject(R.id.refreshView)
    protected PtrFrameLayout refreshView;
    @ViewInject(R.id.recycleView)
    protected RecyclerView recyclerView;
    private ControlService control;
    private List<Results.ObjectsBean> results = new ArrayList<>();
    private ControlAdapter adapter;

    public static final String API = "http://192.168.5.123/";

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
        adapter = new ControlAdapter(results);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        isPrepared = true;
        refreshView.setResistance(1.7f);
        refreshView.setRatioOfHeaderHeightToRefresh(1.2f);
        refreshView.setDurationToClose(200);
        refreshView.setDurationToCloseHeader(1000);
        refreshView.setPullToRefresh(false);
        refreshView.setKeepHeaderWhenRefresh(true);
//        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("SERVICE");
        header.setTextColor(Color.BLUE);
        header.initWithStringArray(R.array.storehouse);
        refreshView.setHeaderView(header);
        refreshView.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                reFresh();
            }
        });
        return view;
    }

    public void setService(ControlService serves) {
        this.control = serves;
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
            }, 150);
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
        control.search("search", 100000000L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Results>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Results results) {
                        refreshView.refreshComplete();
                        adapter.setResults(results.getObjects());
                    }
                });
    }

    @Override
    public void onItemClick(final Switch view, String model, final boolean status) {
        view.setClickable(false);
        control.set("set", model, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                        view.setChecked(!status);
                        view.setClickable(true);
                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.getResult().equals("successful")) {
                            view.setChecked(status);
                        } else {
                            Toast.makeText(getContext(), "修改失败", Toast.LENGTH_SHORT).show();
                            view.setChecked(!status);
                        }
                        view.setClickable(true);
                    }
                });
    }
}
