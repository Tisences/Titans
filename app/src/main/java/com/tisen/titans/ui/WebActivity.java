package com.tisen.titans.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tisen.titans.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by tisen on 2016/12/20 11:52.
 * Email tisences@qq.com
 */
@ContentView(R.layout.activity_web)
public class WebActivity extends BaseActivity {
    @ViewInject(R.id.webView)private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                jump(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.loadUrl(url);
    }
    private void jump(String url){
        Intent intent = new Intent(this,WebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
