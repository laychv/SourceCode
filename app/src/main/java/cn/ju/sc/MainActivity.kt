package cn.ju.sc

import android.content.Intent
import android.os.Bundle
import cn.ju.sc.base.BaseActivity
import cn.ju.sc.dagger2.Dagger2Activity
import cn.ju.sc.eventBus.EventBusActivity
import cn.ju.sc.glide.GlideActivity
import cn.ju.sc.okHttp.OkHttpActivity
import cn.ju.sc.picasso.PicassoActivity
import cn.ju.sc.retrofit.RetrofitActivity
import cn.ju.sc.rxjava2.RxJava2Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClick()
    }

    private fun initClick() {
        //Glide
        glide.setOnClickListener {
            startActivity(Intent(this, GlideActivity::class.java))
        }
        // Picasso
        picasso.setOnClickListener {
            startActivity(Intent(this, PicassoActivity::class.java))
        }
        // eventBus
        eventBus.setOnClickListener {
            startActivity(Intent(this, EventBusActivity::class.java))
        }
        // okHttp
        okHttp.setOnClickListener {
            startActivity(Intent(this, OkHttpActivity::class.java))
        }
        // Retrofit
        retrofit.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
        //RxJava
        rxjava2.setOnClickListener {
            startActivity(Intent(this, RxJava2Activity::class.java))
        }
        // Dagger2
        dagger2.setOnClickListener {
            startActivity(Intent(this, Dagger2Activity::class.java))
        }
    }

}
