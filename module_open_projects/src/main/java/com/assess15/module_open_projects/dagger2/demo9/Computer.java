package com.assess15.module_open_projects.dagger2.demo9;

import javax.inject.Inject;
import javax.inject.Named;

public class Computer {

    @Inject
    @Named("wireless")
    Mouse mMouse;

    public void init(){
        DaggerComputerComponent.builder().build().inject(this);
        System.out.println(mMouse.name());
    }
}
