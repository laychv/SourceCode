package com.lay.open_eventbus

import android.app.Application
import org.greenrobot.eventbus.EventBus

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        EventBus.builder().addIndex(MyEventBusIndex()).build()
    }
}