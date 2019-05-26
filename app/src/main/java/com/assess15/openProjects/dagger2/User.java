package com.assess15.openProjects.dagger2;

import javax.inject.Inject;

public class User {

    String name;

    @Inject
    public User() {
        this.name = "已经注入啦......";
    }
}
