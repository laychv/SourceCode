package com.laychv.module_base.comm

import android.util.Log
import android.view.View
import com.laychv.module_base.BuildConfig

fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

fun log(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.d("", msg)
    }
}