package com.laychv.module_open_projects.dagger2.demo7;

import dagger.Component;

@Component(modules = MouseModule.class)
interface ComputerComponent {
    void inject(Computer computer);
}
