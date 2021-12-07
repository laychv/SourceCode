package com.laychv.module_open_projects.okHttp

import com.laychv.module_base.ui.BaseActivity
import com.laychv.module_open_projects.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_okhttp
    }

    override fun initView() {
        // get sync
        btnSync.setOnClickListener {
            syncRequest()
        }

        // get async
        btnAsync.setOnClickListener {
            asyncRequest()
        }

        // post form
        btnPostForm.setOnClickListener {
            postForm()
        }

        // post json
        btnPostJson.setOnClickListener {
            postJson()
        }
    }

    // dispatcher 1.创建客户端
    private val client = OkHttpClient.Builder()
            .cache(Cache(File("file"), 24 * 1024 * 1024))// 缓存
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
//            .addInterceptor()
//            .dns()
//            .eventListener()
            .build()

    // 2.创建同步请求 阻塞线程
    /**
     * 同步 dispatcher 作用：1.添加同步请求，2.移除同步请求
     */
    private fun syncRequest() {
        val request = Request.Builder().url("http://baidu.com").get().build()
//        request.header()
        // 3.创建桥梁
        val call = client.newCall(request)// Call 是接口，通过实现类realCall实现
        // 4.分水岭
        try {

//            Runnable { // no use
//                val response = call.execute()// 同步
//                Logger.d(response.body()?.string())
//            }

            object : Thread() {
                override fun run() {
                    val response = call.execute()// 同步请求会阻塞主线程抛异常： networkonmainthreadexception
                    Logger.d(response.body()?.string())
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // 2.创建异步请求
    private fun asyncRequest() {
        val request = Request.Builder().url("http://baidu.com").get().build()
        val call = client.newCall(request)
        call.enqueue(object : Callback { // 这里开启工作线程 // 异步
            override fun onFailure(call: Call?, e: IOException?) {//都实在工作线程

            }

            override fun onResponse(call: Call?, response: Response?) {//都在工作线程操作 如果更新UI切换到主线程中
//                Logger.d(response?.body()?.string()) // crash
                runOnUiThread {
                    // string()只能调用一次，后续调用抛异常；因为调用完后关闭I/O流，并清空Buffer，目的就是防止无用的，多的I/O流操作
                    tvContent.text = response?.body()?.string()
                    val byteStream = response?.body()?.byteStream()// 流的方式
                }
            }
        })
    }

    // post form
    private fun postForm() {
        // 1. 初始化client
        val client = OkHttpClient()
        // 2. 创建表单请求体，添加参数
        val formBody = FormBody.Builder().add("search", "Uncle").build()
        // 3. 创建POST请求
        val request = Request.Builder().url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build()
        // 4. 异步请求网络
        client.newCall(request).enqueue(postCallback)
    }

    // post json
    private fun postJson() {
        val json = ""
        val url = ""
        val client = OkHttpClient()
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val requestBody = RequestBody.create(mediaType, json)
        val request = Request.Builder().url(url).post(requestBody).build()
        client.newCall(request).enqueue(postCallback)
    }

    private val postCallback: Callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            val string = response.body()?.string()
            runOnUiThread {
                wv.loadData(string!!, "text/html", "utf-8")
            }
        }
    }

}
