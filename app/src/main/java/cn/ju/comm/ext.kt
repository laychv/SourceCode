package cn.ju.comm

import android.util.Log
import android.view.View
import cn.ju.openProject.BuildConfig

fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

fun log(msg:String) {
    if (BuildConfig.DEBUG) {
        Log.d("", msg)
    }
}