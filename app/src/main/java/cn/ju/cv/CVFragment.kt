package cn.ju.cv

import android.content.Intent
import android.os.Bundle
import cn.ju.comm.BaseFragment
import cn.ju.cv.barChart.BarChartActivity
import cn.ju.cv.codeView.ActivityCodeView
import cn.ju.cv.cureView.CureViewActivity
import cn.ju.cv.lineChart.LineChartActivity
import cn.ju.sc.R
import kotlinx.android.synthetic.main.fragment_cv.*

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
        cureView.setOnClickListener { startActivity(Intent(activity, CureViewActivity::class.java)) }
        codeView.setOnClickListener { startActivity(Intent(activity,ActivityCodeView::class.java)) }
    }

}