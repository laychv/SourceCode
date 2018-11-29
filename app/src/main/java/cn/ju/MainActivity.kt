package cn.ju

import android.os.Bundle
import android.support.annotation.IntRange
import android.support.v4.app.Fragment
import cn.ju.book.BookFragment
import cn.ju.sc.R
import cn.ju.comm.BaseActivity
import cn.ju.comm.FragmentUtils
import cn.ju.cv.CVFragment
import cn.ju.sc.SCFragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var selectIndex: Int = 0
    private var mSCFragment: SCFragment? = null
    private var mCVFragment: CVFragment? = null
    private var bookFragment: BookFragment? = null
    private var mCurrentFragment: Fragment? = null

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initBottomNavBar(selectIndex)
        doOnTabSelected(selectIndex)// 默认
    }

    private fun initBottomNavBar(@IntRange(from = 0, to = 2) position: Int) {
        bottom_navigation_bar.addItem(BottomNavigationItem(R.drawable.vector_drawable_sc, "SC"))
        bottom_navigation_bar.addItem(BottomNavigationItem(R.drawable.vector_drawable_cv, "CV"))
        bottom_navigation_bar.addItem(BottomNavigationItem(R.drawable.vector_drawable_cv, "Book"))
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_FIXED)
        bottom_navigation_bar.activeColor = R.color.colorPrimary
        bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
        bottom_navigation_bar.setFirstSelectedPosition(position)
        bottom_navigation_bar.setTabSelectedListener(tabSelectedListener())
        bottom_navigation_bar.initialise()
    }

    private fun tabSelectedListener() = object : BottomNavigationBar.OnTabSelectedListener {
        override fun onTabSelected(position: Int) {
            doOnTabSelected(position)
        }

        override fun onTabReselected(position: Int) {
        }

        override fun onTabUnselected(position: Int) {
        }

    }

    private fun doOnTabSelected(@IntRange(from = 0, to = 1) position: Int) {
        when (position) {
            0 -> {
                if (mSCFragment == null) {
                    mSCFragment = SCFragment()
                }
                mCurrentFragment = FragmentUtils.switchContent(supportFragmentManager, mCurrentFragment, mSCFragment, content.id, position.toLong(), false)
            }
            1 -> {
                if (mCVFragment == null) {
                    mCVFragment = CVFragment()
                }
                mCurrentFragment = FragmentUtils.switchContent(supportFragmentManager, mCurrentFragment, mCVFragment, content.id, position.toLong(), false)
            }
            2 -> {
                if (bookFragment == null) {
                    bookFragment = BookFragment()
                }
                mCurrentFragment = FragmentUtils.switchContent(supportFragmentManager, mCurrentFragment, bookFragment, content.id, position.toLong(), false)
            }
        }
        selectIndex = position
    }

}
