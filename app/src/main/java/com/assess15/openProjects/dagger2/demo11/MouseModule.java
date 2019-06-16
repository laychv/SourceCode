package com.assess15.openProjects.dagger2.demo11;

import dagger.Module;
import dagger.Provides;

@Module
public class MouseModule {

    @Provides
    Mouse providesWireMouse() {
        return new WireMouse();
    }

}
