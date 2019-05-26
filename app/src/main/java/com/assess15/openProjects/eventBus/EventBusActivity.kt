package com.assess15.openProjects.eventBus

import android.os.Bundle
import com.assess15.openProject.R
import com.assess15.comm.BaseActivity
import kotlinx.android.synthetic.main.activity_event_bus.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_event_bus
    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)//1. register
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)//2.unregister
    }

    private fun initListener() {
        btnEventBus.setOnClickListener {
            // 3.Publisher  / Event
            EventBus.getDefault().post(EventBusMessage("EventBus Already Receive EventBus Message !!!!"))
        }
    }

    // 4.Subscriber
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun tvEventBusMessage(msg: EventBusMessage) {
        tvEventBus.text = msg.message
    }

}