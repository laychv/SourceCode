package com.laychv.module_open_projects.kotlin

import com.laychv.module_base.comm.onClick
import com.laychv.module_base.ui.BaseActivity
import com.laychv.module_open_projects.R
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

/**
 * kotlin Coroutine
 */
class KotlinActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_kotlin
    }

    override fun initView() {
        btnCoroutine.onClick {
            toast(Thread.currentThread().name + Thread.currentThread().id)
            displayDashboard(tvContent)
        }

        btnThread.onClick {
            runBlocking {
                toast(Thread.currentThread().name + Thread.currentThread().id)
            }
            Dashboard().display(tvContent)
        }
    }

}