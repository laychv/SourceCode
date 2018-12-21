package cn.ju.openProject.okHttp

import cn.ju.openProject.R
import cn.ju.comm.BaseActivity
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
        // sync
        btnSync.setOnClickListener {
            syncRequest()
        }

        // async
        btnAsync.setOnClickListener {
            asyncRequest()
        }

    }

    // dispatcher 1.创建客户端
    private val client = OkHttpClient.Builder()
            .cache(Cache(File("file"), 24 * 1024 * 1024))// 缓存
            .readTimeout(3, TimeUnit.SECONDS)
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
                    val response = call.execute()// 同步请求会阻塞主线,程抛异常： networkonmainthreadexception
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
                    tvContent.text = response?.body()?.string()
                }
            }
        })
    }
}
