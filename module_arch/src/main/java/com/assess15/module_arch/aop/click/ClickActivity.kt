package com.assess15.module_arch.aop.click

import com.assess15.module_aop.click.AopOnclick
import com.assess15.module_arch.R
import com.assess15.module_base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_click.*

/**
 * 多次点击aop
 */
class ClickActivity : BaseActivity() {

    var singleSum = 0
    var normalSum = 0

    override fun getLayoutRes(): Int {
        return R.layout.activity_click
    }

    override fun initView() {
        btnClick.setOnClickListener {
            single()
            normal()
        }
    }

    fun normal() {
        tvNormal.text = "正常情况下${normalSum++}"
    }

//    @SingleClick
    @AopOnclick(2000)
    fun single() {
        tvSingle.text = "多次点击下${singleSum++}"
    }
}
