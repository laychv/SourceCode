package cn.ju.sc.glide

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import cn.ju.sc.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun loadImageSimple(context: Context, url: String, target: ImageView) {
    Glide.with(context).load(url).into(target)
}

fun loadImage(activity: Activity, url: String, iv: ImageView) {
    Glide.with(activity)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
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