package com.tisen.titans.service;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.tisen.titans.R;
import com.tisen.titans.api.NewsResult;
import com.tisen.titans.utils.LogUtil;

import org.xutils.x;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tisen on 2016/12/19 16:46.
 * Email tisences@qq.com
 */
public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.Item> {

    private static final String TAG = "ControlAdapter";
    private List<Results.ObjectsBean> results;
    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ControlAdapter(List<Results.ObjectsBean> results) {
        this.results = results;
    }

    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.i("onCreateViewHolder");
        return new Item(LayoutInflater.from(parent.getContext()).inflate(R.layout.control_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Results.ObjectsBean> results) {
        Collections.sort(results, new Comparator<Results.ObjectsBean>() {
            @Override
            public int compare(Results.ObjectsBean objectsBean, Results.ObjectsBean t1) {
                return t1.getAmount() - objectsBean.getAmount();
            }
        });
        this.results = results;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(Item holder, int position) {
        holder.onBind(results.get(position));
    }

    public static interface OnItemClickListener {
        void onItemClick(Switch view, String model, boolean status);
    }

    class Item extends RecyclerView.ViewHolder {
        private TextView amount;
        private TextView model;
        private Switch status;

        public Item(View itemView) {
            super(itemView);
            amount = (TextView) itemView.findViewById(R.id.amount);
            model = (TextView) itemView.findViewById(R.id.model);
            status = (Switch) itemView.findViewById(R.id.status);
        }

        public void onBind(final Results.ObjectsBean objectsBean) {
            LogUtil.i(objectsBean.toString());
            amount.setText(objectsBean.getAmount() + "");
            model.setText(objectsBean.getModel());
            status.setChecked(objectsBean.isStatus());
            status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    onItemClickListener.onItemClick(status, objectsBean.getModel(), b);
                }
            });
        }
    }
}
