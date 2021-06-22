package com.laychv.module_arch

import com.laychv.module_arch.aop.AOPActivity
import com.laychv.module_arch.mvc.view.BookActivity
import com.laychv.module_base.ui.BaseFragment
import kotlinx.android.synthetic.main.activity_arch.*
import org.jetbrains.anko.startActivity

class ArchFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.activity_arch
    }

    override fun initView() {
        btnMVC.setOnClickListener {
            activity?.startActivity<BookActivity>()
        }
        btnMVP.setOnClickListener {
            activity?.startActivity<com.laychv.module_arch.mvp.demo1.BookActivity>()
        }
        btnMVVM.setOnClickListener {
            //              activity?.startActivity<>()
        }
        btnAOP.setOnClickListener {
            activity?.startActivity<AOPActivity>()
        }
    }

}