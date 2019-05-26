package com.assess15.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 获取屏幕宽度
 */
fun getScreenWidth(context: Context): Int {
    val displayMetrics = DisplayMetrics()
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

/**
 * 获取屏幕高度
 */
fun getScreenHeight(context: Context): Int {
    val metric = DisplayMetrics()
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getMetrics(metric)
    return metric.heightPixels
}