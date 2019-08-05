package com.assess15.module_arch.mvc.module;

import com.assess15.module_arch.R;
import com.assess15.module_arch.mvc.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型层
 * 对数据库的操作
 * 通常是对本地数据库的操作或者是通过网络请求获取网络数据的操作
 * 我们在Model里面模拟了一个本地数据库，并提供了增删改查的方法
 */
public class BookModel {

    private static List<Book> list = new ArrayList<>();

    /**
     * 模拟本地数据库
     */
    static {
        list.add(new Book("Java从入门到精通", R.drawable.java));
        list.add(new Book("Android从入门到精通", R.drawable.android));
        list.add(new Book("Java从入门到精通", R.drawable.java));
        list.add(new Book("Android从入门到精通", R.drawable.android));
    }

    /**
     * 添加书本
     * @param name
     * @param image
     */
    public void addBook(String name, int image) {
        list.add(new Book(name, image));
    }

    /**
     * 删除书本
     */
    public void deleteBook( ) {
        list.remove(list.size() - 1);
    }

    /**
     * 查询数据库所有书本
     * @return
     */
    public List<Book> query() {
        return list;
    }

}
