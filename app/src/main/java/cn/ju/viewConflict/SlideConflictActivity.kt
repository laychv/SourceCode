package cn.ju.viewConflict

import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import cn.ju.comm.BaseActivity
import cn.ju.comm.onClick
import cn.ju.openProject.R
import kotlinx.android.synthetic.main.activity_slide_conflict.*
import org.jetbrains.anko.toast

/**
 * =====================================
 * 作    者: ju
 * 版    本：
 * 创建日期： 18-12-25 下午4:35
 * 描    述： 滑动冲突解决 ScrollView / DragImageView
 * =====================================
 */
class SlideConflictActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_slide_conflict
    }

    override fun initView() {
        ivConflict.onClick { toast("Click") }
        ivConflict.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    scrollView.requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_UP -> {
                    scrollView.requestDisallowInterceptTouchEvent(false)
                }
            }
            return@setOnTouchListener false
        }
    }

}