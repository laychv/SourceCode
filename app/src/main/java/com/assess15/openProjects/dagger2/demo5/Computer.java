package com.assess15.openProjects.dagger2.demo5;

import android.util.Log;

import javax.inject.Inject;

import dagger.Lazy;

public class Computer {
    @Inject
    CPU cpu;

    @Inject
    Lazy<USB> usb;

    public void init() {
        DaggerComputerConponent.builder().build().inject(this);
        Log.d("ta", "------------" + usb.get());
        Log.d("ta", "------------" + usb.get());
    }
}
