package com.lay.android_design_pattern.EasyFactoryPattern.factory;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.impl.ApiImpl;
import com.lay.android_design_pattern.EasyFactoryPattern.impl.ApiImpl_A;
import com.lay.android_design_pattern.EasyFactoryPattern.impl.ApiImpl_B;

public class ParamFactory {

    public static Api create(int param) {
        switch (param) {
            case 1:
                return new ApiImpl_A();
            case 2:
                return new ApiImpl_B();
        }
        return new ApiImpl();
    }
}
