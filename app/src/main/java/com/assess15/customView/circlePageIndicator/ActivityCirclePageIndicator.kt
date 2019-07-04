package com.assess15.customView.circlePageIndicator

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.assess15.comm.BaseActivity
import com.assess15.openProject.R
import kotlinx.android.synthetic.main.activity_cpi.*

class ActivityCirclePageIndicator : BaseActivity() {

    private val mDataList = intArrayOf(R.drawable.lao, R.drawable.si, R.drawable.ji, R.drawable.dai, R.drawable.dai, R.drawable.wo)

    override fun getLayoutRes(): Int {
        return R.layout.activity_cpi
    }

    override fun initView() {
        viewPager.adapter = mPagerAdapter
        circlePageIndicator.setViewPager(viewPager)
    }

    private val mPagerAdapter = object : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return mDataList.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(this@ActivityCirclePageIndicator)
            imageView.setImageResource(mDataList[position])
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }

}