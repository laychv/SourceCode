package com.lay.android_plugin_standard;

import android.app.Activity;
import android.os.Bundle;

/**
 * 宿主和插件的标准
 */
public interface ActivityInterface {

    void insertContext(Activity hostActivity);

    void onHostCreate(Bundle saveInstanceState);

    void onHostStart();

    void onHostResume();

    void onHostDestroy();
}
