package cn.ju.kotlin

import android.widget.TextView
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

private val okHttpClient = OkHttpClient()
private val request = Request.Builder().url("https://baidu.com").get().build()

fun displayDashboard(mTextView: TextView) = runBlocking {
    launch {
        mTextView.text =  withContext(Dispatchers.Default) {
            okHttpClient.newCall(request).execute().body()?.string()
        }
    }
}