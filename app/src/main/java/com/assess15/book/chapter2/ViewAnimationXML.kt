package com.assess15.book.chapter2

import android.view.animation.AnimationUtils
import com.assess15.comm.BaseActivity
import com.assess15.comm.onClick
import com.assess15.openProject.R
import kotlinx.android.synthetic.main.activity_view_animation_xml.*

class ViewAnimationXML : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_view_animation_xml
    }

    override fun initView() {
        btnScale.onClick {
            val anim = AnimationUtils.loadAnimation(this,R.anim.scaleanim)
            btnScale.startAnimation(anim)
        }
    }

}