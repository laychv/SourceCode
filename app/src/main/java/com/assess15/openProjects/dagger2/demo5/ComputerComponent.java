package com.assess15.openProjects.dagger2.demo5;

import dagger.Component;

@Component
interface ComputerComponent {
    void inject(Computer computer);
}
