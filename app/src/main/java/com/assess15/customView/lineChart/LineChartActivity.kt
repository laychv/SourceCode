package com.assess15.customView.lineChart

import com.assess15.comm.BaseActivity
import com.assess15.openProject.R
import kotlinx.android.synthetic.main.activity_line_chart.*

class LineChartActivity : BaseActivity() {

    private val HORIZONTAL_AXIS = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    private val DATA = intArrayOf(12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3)

    override fun getLayoutRes(): Int {
        return R.layout.activity_line_chart
    }

    override fun initView() {
        lineChartView.setHorizontalAxis(HORIZONTAL_AXIS)
        lineChartView.setDataList(DATA, 89)
    }
}