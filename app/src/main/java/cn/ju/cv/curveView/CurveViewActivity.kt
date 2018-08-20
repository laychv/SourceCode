package cn.ju.cv.curveView

import android.os.Bundle
import cn.ju.comm.BaseActivity
import cn.ju.sc.R
import kotlinx.android.synthetic.main.activity_cure_view.*

class CurveViewActivity : BaseActivity() {

    private val HORIZONTAL_AXIS = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    private val DATA = intArrayOf(12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cure_view)
        init()
    }

    private fun init() {
        curveView.setDataList(DATA, 89)
        curveView.setHorizontalAxis(HORIZONTAL_AXIS)
    }

}