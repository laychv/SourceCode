package com.lay.open_eventbus

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        findViewById<Button>(R.id.btn).setOnClickListener {
//            EventBus.getDefault().post("str")
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getM(str: String) {
        toast(str)
        findViewById<Button>(R.id.btn).text = str
    }

    override fun onDestroy() {
        super.onDestroy()
//        EventBus.getDefault().unregister(this)
    }

}