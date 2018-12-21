package cn.ju.customView

import android.content.Intent
import cn.ju.comm.BaseFragment
import cn.ju.comm.onClick
import cn.ju.customView.barChart.BarChartActivity
import cn.ju.customView.circlePageIndicator.ActivityCirclePageIndicator
import cn.ju.customView.codeView.ActivityCodeView
import cn.ju.customView.curveView.CurveViewActivity
import cn.ju.customView.focusView.ActivityFocusView
import cn.ju.customView.gradientProgressView.ActivityGradientProgressView
import cn.ju.customView.lineChart.LineChartActivity
import cn.ju.customView.swipeCards.ActivitySwipeCards
import cn.ju.customView.vg.ActivitySizeViewGroup
import cn.ju.openProject.R
import kotlinx.android.synthetic.main.fragment_cv.*
import org.jetbrains.anko.support.v4.startActivity

class CVFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_cv
    }

    override fun initView() {
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
        circleIndicator.onClick {
            startActivity<ActivityCirclePageIndicator>()
        }
        btnFocusView.onClick {
            startActivity<ActivityFocusView>()
        }
    }

}