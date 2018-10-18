package cn.ju.cv.circlePageIndicator

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.ju.comm.BaseActivity
import cn.ju.sc.R
import kotlinx.android.synthetic.main.activity_cpi.*

class ActivityCirclePageIndicator : BaseActivity() {

    private val mDataList = intArrayOf(R.drawable.lao, R.drawable.si, R.drawable.ji, R.drawable.dai, R.drawable.dai, R.drawable.wo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpi)

        initView()
    }

    private fun initView() {
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