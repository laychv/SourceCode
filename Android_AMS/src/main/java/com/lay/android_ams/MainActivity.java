package com.lay.android_ams;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }

    public void init() {
        findViewById(R.id.tvClick).setOnClickListener((v) -> {
            startActivity(new Intent(this, TargetActivity.class));
        });
    }

    public void initView() {
        LinearLayout container = findViewById(R.id.ll_loaner_container);

        for (int i = 0; i < 10; i++) {

            MyView myView = new MyView(this);
            myView.setText("借款号: " + i);

            container.addView(myView);
        }

    }
}