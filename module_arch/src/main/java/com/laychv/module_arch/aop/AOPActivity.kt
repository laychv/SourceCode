package com.laychv.module_arch.aop

import com.laychv.module_arch.R
import com.laychv.module_arch.aop.click.ClickActivity
import com.laychv.module_arch.aop.login.LoginActivity
import com.laychv.module_base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_aop.*
import org.jetbrains.anko.startActivity

class AOPActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_aop
    }

    override fun initView() {
        btnClick.setOnClickListener {
            startActivity<ClickActivity>()
        }
        btnLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

}