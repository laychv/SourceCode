package com.laychv.module_arch.mvc.view

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import com.laychv.module_arch.R
import com.laychv.module_arch.mvc.Book
import com.laychv.module_arch.mvc.BookAdapter
import com.laychv.module_arch.mvc.controller.BookController
import kotlinx.android.synthetic.main.activity_book.*

/**
 * 视图层
 * 发送输入请求给控制器
 * 我们操作Controller获取List数据填充到ListView中，同时可以添加书本和删除书本
 */
class BookActivity : AppCompatActivity(), View.OnClickListener {

    private var bookController: BookController? = null
    private var list: List<Book>? = null
    private var adapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        bt_add!!.setOnClickListener(this)
        bt_delete!!.setOnClickListener(this)

        bookController = BookController()
        list = bookController!!.query()
        adapter = BookAdapter(this, list)
        lv_book!!.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            //添加书本按钮
            R.id.bt_add -> bookController!!.add { adapter!!.notifyDataSetChanged() }
            //删除书本按钮
            R.id.bt_delete -> bookController!!.delete { adapter!!.notifyDataSetChanged() }
        }
    }
}