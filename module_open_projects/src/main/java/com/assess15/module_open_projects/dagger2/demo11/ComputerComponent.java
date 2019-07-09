package com.assess15.module_open_projects.dagger2.demo11;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MouseModule.class)
public interface ComputerComponent {
    void inject(Computer computer);
}
