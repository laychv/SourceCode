package cn.ju.book.chapter2

import android.view.animation.AnimationUtils
import cn.ju.comm.BaseActivity
import cn.ju.comm.onClick
import cn.ju.sc.R
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