package com.assess15.openProjects.dagger2.demo6;

import dagger.Component;

@Component
interface ComputerComponent {
    public void inject(Computer computer);
}
