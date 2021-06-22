package com.laychv.module_open_projects.dagger2Android

import com.laychv.module_base.comm.onClick
import com.laychv.module_base.ui.BaseActivity
import com.laychv.module_open_projects.R
import com.laychv.module_open_projects.dagger2Android.demo1.DaggerAndroidActivity
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
            startActivity<DaggerAndroidActivity>()
        }
        btn2.onClick {

        }
        btn3.onClick {

        }
    }

}