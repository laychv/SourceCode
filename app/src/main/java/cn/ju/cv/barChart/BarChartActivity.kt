package cn.ju.cv.barChart

import android.os.Bundle
import cn.ju.comm.BaseActivity
import cn.ju.sc.R
import kotlinx.android.synthetic.main.activity_bar_chart.*

class BarChartActivity : BaseActivity() {

    private val HORIZONTAL_AXIS = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")

    private val DATA = floatArrayOf(12f, 24f, 45f, 56f, 89f, 70f, 49f, 22f, 23f, 10f, 12f, 3f)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        initView()
    }

    private fun initView() {
        barChartView.setHorizontalAxis(HORIZONTAL_AXIS)
        barChartView.setDataList(DATA, 89)
    }
}