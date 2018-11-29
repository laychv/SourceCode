package cn.ju.cv.gradientProgressView

import cn.ju.comm.BaseActivity
import cn.ju.sc.R
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