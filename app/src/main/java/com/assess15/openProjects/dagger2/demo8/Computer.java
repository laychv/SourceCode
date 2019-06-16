package com.assess15.openProjects.dagger2.demo8;

import javax.inject.Inject;

public class Computer {

    @Inject
    @MouseType("wired")
    Mouse mMouse;

    public void init() {
        DaggerComputerComponent.builder().build().inject(this);
        System.out.println(mMouse.name());
    }
}
