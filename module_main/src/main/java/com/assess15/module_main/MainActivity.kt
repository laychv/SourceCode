package com.assess15.module_main

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.assess15.module_arch.ArchFragment
import com.assess15.module_base.arouter.ARouterPath.module_main.Companion.main_activity
import com.assess15.module_base.ui.BaseActivity
import com.assess15.module_base.utils.FragmentUtils
import com.assess15.module_open_projects.OpenProjectsFragment
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = main_activity)
class MainActivity : BaseActivity() {

    private var selectIndex: Int = 0
    private var mOPFragment: OpenProjectsFragment? = null
    private var mArchFragment: ArchFragment? = null
    //    private var bookFragment: BookFragment? = null
    private var mCurrentFragment: Fragment? = null

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initBottomNavBar(selectIndex)
        doOnTabSelected(selectIndex)// 默认
    }

    private fun initBottomNavBar(@androidx.annotation.IntRange(from = 0, to = 2) position: Int) {
        bottom_navigation_bar.addItem(BottomNavigationItem(R.drawable.vector_drawable_sc, "OP"))
        bottom_navigation_bar.addItem(BottomNavigationItem(R.drawable.vector_drawable_cv, "CV"))
//        bottom_navigation_bar.addItem(BottomNavigationItem(R.drawable.vector_drawable_cv, "Book"))
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

    private fun doOnTabSelected(@androidx.annotation.IntRange(from = 0, to = 1) position: Int) {
        when (position) {
            0 -> {
                if (mOPFragment == null) {
                    mOPFragment = OpenProjectsFragment()
                }
                mCurrentFragment = FragmentUtils.switchContent(supportFragmentManager, mCurrentFragment, mOPFragment, content.id, position.toLong(), false)
            }
            1 -> {
                if (mArchFragment == null) {
                    mArchFragment = ArchFragment()
                }
                mCurrentFragment = FragmentUtils.switchContent(supportFragmentManager, mCurrentFragment, mArchFragment, content.id, position.toLong(), false)
            }
//            2 -> {
//                if (bookFragment == null) {
//                    bookFragment = BookFragment()
//                }
//                mCurrentFragment = FragmentUtils.switchContent(supportFragmentManager, mCurrentFragment, bookFragment, content.id, position.toLong(), false)
//            }
        }
        selectIndex = position
    }


}