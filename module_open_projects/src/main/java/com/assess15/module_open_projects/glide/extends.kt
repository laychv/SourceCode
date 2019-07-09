package com.assess15.module_open_projects.glide

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.widget.ImageView
import com.assess15.module_open_projects.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun loadImageSimple(context: Context, url: String, target: ImageView) {
    Glide.with(context).load(url).into(target)
}

fun loadImage(activity: AppCompatActivity, url: String, iv: ImageView) {
    Glide.with(activity)
            .load(url)
            .placeholder(R.drawable.ic_logo)
            .error(R.drawable.ic_logo)
            .override(300, 300)
            .fitCenter()
            .centerCrop()
            .skipMemoryCache(true)// 不使用内存缓存
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .diskCacheStrategy(DiskCacheStrategy.RESULT)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .priority(Priority.HIGH)
            .into(iv)
}