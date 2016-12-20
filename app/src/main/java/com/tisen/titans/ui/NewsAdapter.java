package com.tisen.titans.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tisen.titans.R;
import com.tisen.titans.api.NewsResult;
import com.tisen.titans.utils.LogUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by tisen on 2016/12/19 16:46.
 * Email tisences@qq.com
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsView> {

    private List<NewsResult.ResultBean.DataBean> results;
    private Activity activity;
    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsAdapter(List<NewsResult.ResultBean.DataBean> results, Activity activity) {
        this.results = results;
        this.activity = activity;
    }

    @Override
    public NewsView onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.i("onCreateViewHolder");
        return new NewsView(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public void setResults(List<NewsResult.ResultBean.DataBean>results){
        this.results = results;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(NewsView holder, int position) {
        holder.onBind(results.get(position));
    }
    public static interface OnItemClickListener{
        void onItemClick(View view, NewsResult.ResultBean.DataBean dataBean);
    }
    class NewsView extends RecyclerView.ViewHolder {
        View view;
        TextView title;
        ImageView image1;
        ImageView image2;
        ImageView image3;
        TextView time;
        TextView author;

        public NewsView(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            image1 = (ImageView) itemView.findViewById(R.id.image1);
            image2 = (ImageView) itemView.findViewById(R.id.image2);
            image3 = (ImageView) itemView.findViewById(R.id.image3);
            time = (TextView) itemView.findViewById(R.id.time);
            author = (TextView) itemView.findViewById(R.id.author);
        }
        public void onBind(final NewsResult.ResultBean.DataBean news){
            LogUtil.i(news.toString());
            title.setText(news.getTitle());
            x.image().bind(image1,news.getThumbnail_pic_s());
            x.image().bind(image2,news.getThumbnail_pic_s02());
            x.image().bind(image3,news.getThumbnail_pic_s03());
            time.setText(news.getDate());
            author.setText(news.getAuthor_name());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(v,news);
                    }
                }
            });
        }
    }
}
