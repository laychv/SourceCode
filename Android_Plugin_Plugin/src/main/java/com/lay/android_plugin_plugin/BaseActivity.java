package com.lay.android_plugin_plugin;

import android.app.Activity;
import android.os.Bundle;

import com.lay.android_plugin_standard.ActivityInterface;

public class BaseActivity extends Activity implements ActivityInterface {

    Activity hostActivity;

    @Override
    public void insertContext(Activity hostActivity) {
        this.hostActivity = hostActivity;
    }

    @Override
    public void onHostCreate(Bundle saveInstanceState) {

    }

    @Override
    public void onHostStart() {

    }

    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostDestroy() {

    }
}
