package com.lay.android_design_pattern.EasyFactoryPattern.impl;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.bean.UserInfo;

public class ApiImpl implements Api {

    @Override
    public UserInfo create() {
        UserInfo userInfo = new UserInfo();
        System.out.println(">>>" + userInfo);
        return userInfo;
    }
}
