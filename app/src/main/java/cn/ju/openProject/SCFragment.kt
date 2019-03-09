package cn.ju.openProject

import android.content.Intent
import cn.ju.comm.BaseFragment
import cn.ju.comm.onClick
import cn.ju.kotlin.KotlinActivity
import cn.ju.openProject.dagger2.Dagger2Activity
import cn.ju.openProject.eventBus.EventBusActivity
import cn.ju.openProject.glide.GlideActivity
import cn.ju.openProject.okHttp.OkHttpActivity
import cn.ju.openProject.picasso.PicassoActivity
import cn.ju.openProject.retrofit.RetrofitActivity
import cn.ju.openProject.rx.rxbinding.ActivityRxBinding
import cn.ju.openProject.rx.rxjava2.RxJava2Activity
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