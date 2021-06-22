package com.laychv.module_arch.mvc.controller;

import com.laychv.module_arch.R;
import com.laychv.module_arch.mvc.Book;
import com.laychv.module_arch.mvc.module.BookModel;

import java.util.List;

/**
 * 控制器层
 * 处理业务逻辑，调用模型层的操作，并对外暴露接口
 * 根据Model层的方法，加上我们的业务逻辑处理，对外提供方法并暴露接口
 * 看delete这个方法，判断List是否为空（业务逻辑），使用mode.deleteBook()（Model层方法），通过listener.onComplete()（暴露接口）
 */
public class BookController {

    private BookModel mode;

    public BookController() {
        mode = new BookModel();
    }

    /**
     * 添加书本
     *
     * @param listener
     */
    public void add(onAddBookListener listener) {
        mode.addBook("JavaWeb从入门到精通", R.drawable.javaweb);
        if (listener != null) {
            listener.onComplete();
        }
    }

    /**
     * 删除书本
     *
     * @param listener
     */
    public void delete(onDeleteBookListener listener) {
        if (mode.query().isEmpty()) {
            return;
        } else {
            mode.deleteBook();
        }
        if (listener != null) {
            listener.onComplete();
        }
    }

    /**
     * 查询所有书本
     *
     * @return
     */
    public List<Book> query() {
        return mode.query();
    }

    /**
     * 添加成功的回调接口
     */
    public interface onAddBookListener {
        void onComplete();
    }

    /**
     * 删除成功的回调接口
     */
    public interface onDeleteBookListener {
        void onComplete();
    }
}