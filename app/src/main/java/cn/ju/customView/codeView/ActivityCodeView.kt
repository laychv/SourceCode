package cn.ju.customView.codeView

import android.graphics.Color
import android.view.View
import cn.ju.comm.BaseActivity
import cn.ju.openProject.R
import kotlinx.android.synthetic.main.activity_code_view.*
import android.widget.Toast

class ActivityCodeView : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_code_view
    }

    override fun initView() {

    }

    //刷新
    fun refresh(view: View) {
        code.refresh()
    }

    /**
     * 改变颜色
     * @param view
     */
    fun changeColor(view: View) {
        code.setColor(Color.BLUE)
    }

    /**
     * 改变字体大小
     * @param view
     */
    fun changeFont(view: View) {
        code.setFontSize(100)
    }

    /**
     * 改变随机数个数
     * @param view
     */
    fun textCount(view: View) {
        code.setCount(5)
    }

    /**
     * 改变干扰线条数
     * @param view
     */
    fun lineCount(view: View) {
        code.setLineCount(150)
    }

    /**
     * 获取验证码
     * @param view
     */
    operator fun get(view: View) {
        val code = code.theCode()
        Toast.makeText(this, code, Toast.LENGTH_LONG).show()
    }
}