package com.tisen.titans.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.tisen.titans.R;
import com.tisen.titans.utils.TextUtil;

public class LoadActivity extends BaseActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(TextUtil.getSpannable("A Place Nearby", getResources().getColor(R.color.black),1.0f,
                " - Linda Sundblad",getResources().getColor(R.color.grey),0.7f));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(TextUtil.getSpannable("A Place Nearby", getResources().getColor(R.color.firebrick),1.0f,
                        " - Linda Sundblad",getResources().getColor(R.color.firebrick),0.7f));
            }
        });
    }
}
