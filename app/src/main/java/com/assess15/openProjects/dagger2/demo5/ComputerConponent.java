package com.assess15.openProjects.dagger2.demo5;

import dagger.Component;

@Component
interface ComputerConponent {
    void inject(Computer computer);
}
