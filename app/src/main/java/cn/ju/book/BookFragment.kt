package cn.ju.book

import cn.ju.book.chapter2.ViewAnimationXML
import cn.ju.comm.BaseFragment
import cn.ju.comm.onClick
import cn.ju.openProject.R
import kotlinx.android.synthetic.main.fragment_book.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * book
 */
class BookFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_book
    }

    override fun initView() {
        btnChapter2.onClick { startActivity<ViewAnimationXML>() }
    }

}