package com.assess15.module_open_projects.dagger2.demo7;

import dagger.Module;
import dagger.Provides;

@Module
public class MouseModule {

    @Provides
    Mouse ProvidesWireMouse() {
        return new WireMouse();
    }

    /**
     * 这里为了编译通过，所以注释了，没注释的话，编译
     */
//    @Provides
//    Mouse ProvidesWirelessMouse() {
//        return new WirelessMouse();
//    }
}
