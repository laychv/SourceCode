package com.assess15.openProjects.dagger2.demo9;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MouseModule {

    @Provides
    @Named("wired")
    Mouse ProvidesWireMouse() {
        return new WireMouse();
    }

    @Provides
    @Named("wireless")
    Mouse ProvidesWirelessMouse() {
        return new WirelessMouse();
    }
}
