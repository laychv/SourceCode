package com.laychv

import com.alibaba.android.arouter.launcher.ARouter
import com.laychv.module_base.comm.onClick
import com.laychv.module_base.ui.BaseActivity
import com.laychv.module_base.arouter.ARouterPath.module_main.Companion.main_activity
import com.laychv.openProject.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        btn.onClick {
            ARouter.getInstance().build(main_activity).navigation()
            finish()
        }
    }

}
