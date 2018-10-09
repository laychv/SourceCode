package cn.ju.cv.gradientProgressView

import android.os.Bundle
import cn.ju.comm.BaseActivity
import cn.ju.sc.R
import kotlinx.android.synthetic.main.activity_gradient_progress_view.*

/**
 * 渐变圆弧进度条
 */
class ActivityGradientProgressView : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gradient_progress_view)

        startAnimation.setOnClickListener {
            progress.startAnimation(100)
        }
    }

}