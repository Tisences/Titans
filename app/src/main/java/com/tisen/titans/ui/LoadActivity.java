package com.tisen.titans.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Target;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
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
        layout = (RelativeLayout) findViewById(R.id.root);
        viewPager = (RollPagerView) findViewById(R.id.rollPagerView);
        viewPager.setHintView(new ColorPointHintView(this,getResources().getColor(R.color.red),getResources().getColor(R.color.grey)));
        viewPager.setPlayDelay(2000);
        viewPager.setAdapter(new TestLoopAdapter(viewPager));
        viewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtil.i(position+"");
            }
        });

    }
    class TestLoopAdapter extends LoopPagerAdapter{
        private String[] imgs = {
                "http://img.hb.aicdn.com/69f4f8eaf785ed9ab07c7579d6e71e0045bb3bb6a800-Y2VdKg_fw658",
                "http://img.hb.aicdn.com/286261de80d9c34b8574dfaad6f68022c1e09681ba12-y7e7Uf_fw658",
                "http://img.hb.aicdn.com/ad3c46028b8247e633b870e1ba9824d685fad8275c689-MySMha_fw658",
                "http://img.hb.aicdn.com/8f6707fc0d74675c97aeb5f6e2d91997728e8083f0f78-r6Q7Yn_fw658"
        };
        private ImageOptions options = new ImageOptions.Builder().setUseMemCache(true).build();

        public TestLoopAdapter(RollPagerView view){
            super(view);
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
        public int getRealCount() {
            return imgs.length;
        }
    }
}
