package com.assess15.module_open_projects.dagger2Android.demo1

import android.os.Bundle
import com.assess15.module_base.ui.BaseActivity
import com.assess15.module_open_projects.R
import dagger.android.AndroidInjection

class MainActivity : BaseActivity() {

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