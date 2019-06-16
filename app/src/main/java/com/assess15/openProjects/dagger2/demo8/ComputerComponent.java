package com.assess15.openProjects.dagger2.demo8;

import dagger.Component;

@Component(modules = MouseModule.class)
interface ComputerComponent {
    void inject(Computer computer);
}
