package com.tisen.titans.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Target;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.HintView;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.tisen.titans.R;
import com.tisen.titans.utils.BitmapUtil;
import com.tisen.titans.utils.LogUtil;
import com.tisen.titans.utils.TextUtil;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class LoadActivity extends BaseActivity {

    private RelativeLayout layout;
    private RollPagerView viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION|WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        layout = (RelativeLayout) findViewById(R.id.root);
        viewPager = (RollPagerView) findViewById(R.id.rollPagerView);
        viewPager.setHintView(new ColorPointHintView(this,getResources().getColor(R.color.red),getResources().getColor(R.color.grey)));
//        viewPager.setPlayDelay(2000);
        viewPager.setAdapter(new TestLoopAdapter());
        viewPager.setHintPadding(0,0,50,0);
        viewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtil.i(position+"");
            }
        });

    }
    class TestLoopAdapter extends StaticPagerAdapter{
        private String[] imgs = {
                "http://img.hb.aicdn.com/f9c53d57f3accd00a4c8ff0eb6d52929810359fa356ec-a5Jdbv",
                "http://img.hb.aicdn.com/9174ac0ba828c7a493fb3b7dd568421dcfe3355227d19-1Q6ayh",
                "http://img.hb.aicdn.com/436ba9af58cc0cb82304db8d537a2b01ea05d8401acd4-N6hVGA",
                "http://img.hb.aicdn.com/c39ac6a698b6d95b823d0840a773bdb7f2cc057216dfd-HkHx3k"
        };
        private ImageOptions options = new ImageOptions.Builder().setUseMemCache(true).build();

        public TestLoopAdapter(){
            super();
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            x.image().bind(imageView, imgs[position],options);
            return imageView;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
