package com.assess15.customView.gradientProgressView

import com.assess15.comm.BaseActivity
import com.assess15.openProject.R
import kotlinx.android.synthetic.main.activity_gradient_progress_view.*

/**
 * 渐变圆弧进度条
 */
class ActivityGradientProgressView : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_gradient_progress_view
    }

    override fun initView() {
        startAnimation.setOnClickListener {
            progress.startAnimation(100)
        }
    }

}