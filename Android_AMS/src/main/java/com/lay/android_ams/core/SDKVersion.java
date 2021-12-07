package com.lay.android_ams.core;

import android.os.Build;

/**
 * SDK AMS 区别较大
 */
public class SDKVersion {

    // android [21-25]
    public static boolean isSDK21to25() {
        int sdk = Build.VERSION.SDK_INT;
        return sdk <= 25;
    }

    // android [26-28]
    public static boolean isSDK26to28() {
        int sdk = Build.VERSION.SDK_INT;
        return sdk >= 26 && sdk <= 28;
    }

    // android [29-30)
    public static boolean isSDK29to30() {
        int sdk = Build.VERSION.SDK_INT;
        return sdk >= 29 && sdk <= 30;
    }

}
