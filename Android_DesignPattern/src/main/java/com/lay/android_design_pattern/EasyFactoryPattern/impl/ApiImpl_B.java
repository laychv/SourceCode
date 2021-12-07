package com.lay.android_design_pattern.EasyFactoryPattern.impl;

import android.util.Log;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.bean.UserInfo;

public class ApiImpl_B implements Api {
    @Override
    public UserInfo create() {
        UserInfo userInfo = new UserInfo("lay", 20);
        System.out.println("impl B >>>" + userInfo.toString());
        Log.d("impl B >>", userInfo.toString());
        return userInfo;
    }
}
