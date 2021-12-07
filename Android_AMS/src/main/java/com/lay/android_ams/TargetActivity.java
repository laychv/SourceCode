package com.lay.android_ams;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 正常流程是需要通过在AndroidManifest.xml中注册才能运行,
 * 否则报错:android.content.ActivityNotFoundException: Unable to find explicit activity class {com.lay.android_ams/com.lay.android_ams.TargetActivity}; have you declared this activity in your AndroidManifest.xml?
 * 现在通过hook AMS方式, 绕过正常流程, 实现无需xml中注册也能正常启动TargetActivity
 */
public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "跳转到TargetActivity成功!没有在xml注册哦!!!", Toast.LENGTH_SHORT).show();
    }
}
