package com.assess15.module_open_projects.dagger2.demo11;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainBoardModule {

    @Singleton
    @Provides
    MainBoard providesMainBoard() {
        return new MainBoard();
    }

}
