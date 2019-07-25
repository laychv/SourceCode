package com.assess15

import com.alibaba.android.arouter.launcher.ARouter
import com.assess15.module_base.comm.onClick
import com.assess15.module_base.ui.BaseActivity
import com.assess15.module_base.arouter.ARouterPath.module_main.Companion.main_activity
import com.assess15.openProject.R
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
