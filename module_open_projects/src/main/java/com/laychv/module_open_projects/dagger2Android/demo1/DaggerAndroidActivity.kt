package com.laychv.module_open_projects.dagger2Android.demo1

import android.os.Bundle
import com.laychv.module_base.ui.BaseActivity
import com.laychv.module_open_projects.R
import dagger.android.AndroidInjection

class DaggerAndroidActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main1
    }

    override fun initView() {
    }

}