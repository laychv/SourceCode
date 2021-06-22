package com.laychv.module_arch.mvp.demo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laychv.module_arch.R
import com.laychv.module_arch.mvc.Book
import com.laychv.module_arch.mvc.BookAdapter
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {

    lateinit var bookPresenter: BookPresenter
    private var list: List<Book>? = null
    private var adapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        initView()
        bookPresenter = BookPresenter()
    }

    fun initView() {
        adapter = BookAdapter(this, list)
        lv_book!!.adapter = adapter

        bt_add.setOnClickListener {
            bookPresenter.add()
        }
        bt_delete.setOnClickListener {
            bookPresenter.delete()
        }
    }
}