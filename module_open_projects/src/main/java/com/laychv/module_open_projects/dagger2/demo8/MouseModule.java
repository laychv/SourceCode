package com.laychv.module_open_projects.dagger2.demo8;

import dagger.Module;
import dagger.Provides;

@Module
public class MouseModule {
    @Provides
    @MouseType("wired")// 指定该方法返回有线鼠标对象
    Mouse providesWiredMouse(){
       return new WireMouse();
    }

    @Provides
    @MouseType("wireless")// 指定该方法返回无线鼠标对象
    Mouse providesWirelessMouse(){
        return new WirelessMouse();
    }
}
