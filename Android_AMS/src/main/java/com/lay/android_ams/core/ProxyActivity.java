package com.lay.android_ams.core;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 这是代理的Activity, 但是必须在AndroidManifest.xml中注册, 通过ProxyActivity来欺骗AMS
 * 相当于在AndroidManifest.xml中占位, 以后再有其他Activity创建都不用在AndroidManifest.xml中注册,
 * 都是使用这个ProxyActivity在AndroidManifest.xml中创建的
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "这是代理Activity.....", Toast.LENGTH_LONG).show();
    }
}
