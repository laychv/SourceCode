package com.assess15.book

import com.assess15.book.chapter2.ViewAnimationXML
import com.assess15.comm.BaseFragment
import com.assess15.comm.onClick
import com.assess15.openProject.R
import kotlinx.android.synthetic.main.fragment_book.*
import org.jetbrains.anko.startActivity

/**
 * book
 */
class BookFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_book
    }

    override fun initView() {
        btnChapter2.onClick {
            activity?.startActivity<ViewAnimationXML>()
        }
    }

}