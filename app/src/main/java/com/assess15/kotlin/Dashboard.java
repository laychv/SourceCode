package com.assess15.kotlin;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Dashboard {

    private OkHttpClient okhttp = new OkHttpClient();
    private Request request = new Request.Builder().url("http://baidu.com").get().build();
    private Handler handler = new Handler(Looper.getMainLooper());

    public void display(TextView mTextView) {
        okhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                String string = response.body().string();
                handler.post(() -> mTextView.setText(string));
            }
        });
    }
}
