package com.lay.android_plugin_plugin;

import android.os.Bundle;

public class PluginActivity extends BaseActivity {

    @Override
    public void onHostCreate(Bundle saveInstanceState) {
        super.onHostCreate(saveInstanceState);
        setContentView(R.layout.activity_plugin);
    }

    @Override
    public void onHostDestroy() {
        super.onHostDestroy();
    }
}