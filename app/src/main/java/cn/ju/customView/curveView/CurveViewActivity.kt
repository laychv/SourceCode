package cn.ju.customView.curveView

import cn.ju.comm.BaseActivity
import cn.ju.openProject.R
import kotlinx.android.synthetic.main.activity_cure_view.*

class CurveViewActivity : BaseActivity() {

    private val HORIZONTAL_AXIS = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    private val DATA = intArrayOf(12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3)

    override fun getLayoutRes(): Int {
        return R.layout.activity_cure_view
    }

    override fun initView() {
        init()
    }

    private fun init() {
        curveView.setDataList(DATA, 89)
        curveView.setHorizontalAxis(HORIZONTAL_AXIS)
    }

}