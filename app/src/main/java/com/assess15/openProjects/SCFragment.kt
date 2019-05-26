package com.assess15.openProjects

import android.content.Intent
import com.assess15.comm.BaseFragment
import com.assess15.comm.onClick
import com.assess15.kotlin.KotlinActivity
import com.assess15.openProject.R
import com.assess15.openProjects.dagger2.Dagger2Activity
import com.assess15.openProjects.eventBus.EventBusActivity
import com.assess15.openProjects.glide.GlideActivity
import com.assess15.openProjects.okHttp.OkHttpActivity
import com.assess15.openProjects.picasso.PicassoActivity
import com.assess15.openProjects.retrofit.RetrofitActivity
import com.assess15.openProjects.rx.rxbinding.ActivityRxBinding
import com.assess15.openProjects.rx.rxjava2.RxJava2Activity
import kotlinx.android.synthetic.main.fragment_sc.*
import org.jetbrains.anko.support.v4.startActivity

class SCFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_sc
    }

    override fun initView() {
        //Glide
        glide.setOnClickListener {
            startActivity(Intent(activity, GlideActivity::class.java))
        }
        // Picasso
        picasso.setOnClickListener {
            startActivity(Intent(activity, PicassoActivity::class.java))
        }
        // eventBus
        eventBus.setOnClickListener {
            startActivity(Intent(activity, EventBusActivity::class.java))
        }
        // okHttp
        okHttp.setOnClickListener {
            startActivity(Intent(activity, OkHttpActivity::class.java))
        }
        // Retrofit
        retrofit.setOnClickListener {
            startActivity(Intent(activity, RetrofitActivity::class.java))
        }
        //RxJava
        rxjava2.onClick {
            startActivity(Intent(activity, RxJava2Activity::class.java))
        }
        // rxbinding
        rxbinding.onClick {
            startActivity<ActivityRxBinding>()
        }
        // Dagger2
        dagger2.setOnClickListener {
            startActivity(Intent(activity, Dagger2Activity::class.java))
        }
        // kotlin
        kotlin.onClick {
            startActivity<KotlinActivity>()
        }
    }

}