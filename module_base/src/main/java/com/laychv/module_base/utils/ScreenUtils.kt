package com.laychv.module_base.utils

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

fun dip2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}