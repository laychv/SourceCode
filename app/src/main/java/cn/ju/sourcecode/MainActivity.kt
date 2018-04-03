package cn.ju.sourcecode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.ju.sourcecode.eventBus.EventBusActivity
import cn.ju.sourcecode.okHttp.OkHttpActivity
import cn.ju.sourcecode.retrofit.RetrofitActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initClick()
    }

    private fun initClick() {
        // okHttp
        okHttp.setOnClickListener {
            startActivity(Intent(this, OkHttpActivity::class.java))
        }
        // eventBus
        eventBus.setOnClickListener {
            startActivity(Intent(this, EventBusActivity::class.java))
        }
        // Retrofit
        retrofit.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
    }

}
