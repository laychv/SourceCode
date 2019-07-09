package com.assess15.module_open_projects.kotlin

import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

private val okHttpClient = OkHttpClient()
private val request = Request.Builder().url("https://baidu.com").get().build()

fun displayDashboard(mTextView: TextView) = runBlocking {
    launch {
        mTextView.text = withContext(Dispatchers.Default) {
            okHttpClient.newCall(request).execute().body()?.string()
        }
    }
}