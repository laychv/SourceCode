package cn.ju.cv

import android.content.Intent
import android.os.Bundle
import cn.ju.comm.BaseFragment
import cn.ju.cv.barChart.BarChartActivity
import cn.ju.cv.codeView.ActivityCodeView
import cn.ju.cv.curveView.CurveViewActivity
import cn.ju.cv.gradientProgressView.ActivityGradientProgressView
import cn.ju.cv.lineChart.LineChartActivity
import cn.ju.cv.swipeCards.ActivitySwipeCards
import cn.ju.cv.vg.ActivitySizeViewGroup
import cn.ju.sc.R
import kotlinx.android.synthetic.main.fragment_cv.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

class CVFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_cv
    }

    override fun initView(savedInstanceState: Bundle?) {
        // barChart
        barChart.setOnClickListener { startActivity(Intent(activity, BarChartActivity::class.java)) }
        // lineChart
        lineChart.setOnClickListener { startActivity(Intent(activity, LineChartActivity::class.java)) }
        // cureview
        cureView.setOnClickListener { startActivity(Intent(activity, CurveViewActivity::class.java)) }
        codeView.setOnClickListener { startActivity(Intent(activity, ActivityCodeView::class.java)) }
        cureView.setOnClickListener { startActivity(Intent(activity, CurveViewActivity::class.java)) }
        sizeVG.setOnClickListener { startActivity(Intent(activity, ActivitySizeViewGroup::class.java)) }
        progressView.onClick {
            startActivity<ActivityGradientProgressView>()
        }
        swipeCards.onClick {
            startActivity<ActivitySwipeCards>()
        }
    }

}