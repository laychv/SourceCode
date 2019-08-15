package com.assess15.module_open_projects.dagger2Android

import com.assess15.module_base.comm.onClick
import com.assess15.module_base.ui.BaseActivity
import com.assess15.module_open_projects.R
import com.assess15.module_open_projects.dagger2Android.demo1.MainActivity
import kotlinx.android.synthetic.main.activity_dagger2_android.*
import org.jetbrains.anko.startActivity

/**
 * Dagger2 Android
 */
class Dagger2AndroidActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_dagger2_android
    }

    override fun initView() {
        btn1.onClick {
            startActivity<MainActivity>()
        }
        btn2.onClick {

        }
        btn3.onClick {

        }
    }

}