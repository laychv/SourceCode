package com.lay.android_handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        FrameLayout frameLayout = new FrameLayout(this);

        frameLayout.addView(textView);
        setContentView(frameLayout);
        init();
    }

    public void init() {

        startActivity(new Intent(this, MainActivity.class));

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                textView.setText("ssss");
            }
        }).start();
    }
}
