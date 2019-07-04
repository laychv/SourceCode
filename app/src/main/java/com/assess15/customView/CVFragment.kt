package com.assess15.customView

import android.content.Intent
import com.assess15.comm.BaseFragment
import com.assess15.comm.onClick
import com.assess15.customView.barChart.BarChartActivity
import com.assess15.customView.circlePageIndicator.ActivityCirclePageIndicator
import com.assess15.customView.codeView.ActivityCodeView
import com.assess15.customView.curveView.CurveViewActivity
import com.assess15.customView.focusView.ActivityFocusView
import com.assess15.customView.gradientProgressView.ActivityGradientProgressView
import com.assess15.customView.lineChart.LineChartActivity
import com.assess15.customView.swipeCards.ActivitySwipeCards
import com.assess15.customView.vg.ActivitySizeViewGroup
import com.assess15.openProject.R
import com.assess15.viewConflict.SlideConflictActivity
import kotlinx.android.synthetic.main.fragment_cv.*
import org.jetbrains.anko.startActivity

class CVFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_cv
    }

    override fun initView() {
        // barChart
        barChart.setOnClickListener { startActivity(Intent(activity, BarChartActivity::class.java)) }
        lineChart
        lineChart.setOnClickListener { startActivity(Intent(activity, LineChartActivity::class.java)) }
//         cureview
        cureView.setOnClickListener { startActivity(Intent(activity, CurveViewActivity::class.java)) }
        codeView.setOnClickListener { startActivity(Intent(activity, ActivityCodeView::class.java)) }
        cureView.setOnClickListener { startActivity(Intent(activity, CurveViewActivity::class.java)) }
        sizeVG.setOnClickListener { startActivity(Intent(activity, ActivitySizeViewGroup::class.java)) }
        progressView.onClick {
            activity?.startActivity<ActivityGradientProgressView>()
        }
        swipeCards.onClick {
            activity?.startActivity<ActivitySwipeCards>()
        }
        circleIndicator.onClick {
            activity?.startActivity<ActivityCirclePageIndicator>()
        }
        btnFocusView.onClick {
            activity?.startActivity<ActivityFocusView>()
        }
        btnConflict.onClick {
            activity?.startActivity<SlideConflictActivity>()
        }
    }

}