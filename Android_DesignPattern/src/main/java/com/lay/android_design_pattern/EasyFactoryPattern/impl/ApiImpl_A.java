package com.lay.android_design_pattern.EasyFactoryPattern.impl;

import android.util.Log;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.bean.UserInfo;

public class ApiImpl_A implements Api {
    @Override
    public UserInfo create() {
        UserInfo userInfo = new UserInfo("chv", 30);
        System.out.println("impl A >>>" + userInfo.toString());
        Log.d("impl A >>", userInfo.toString());
        return userInfo;
    }
}
