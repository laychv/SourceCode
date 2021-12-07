package com.lay.android_design_pattern.EasyFactoryPattern.factory;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.impl.ApiImpl;

public class ApiFactory {

    public static Api createApi() {
        return new ApiImpl();
    }
}
