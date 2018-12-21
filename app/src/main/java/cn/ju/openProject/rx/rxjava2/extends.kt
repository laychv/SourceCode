package cn.ju.openProject.rx.rxjava2

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 作用域
 * subscribeOn(Schedulers.io())  ->   Observable.create
 * observeOn(AndroidSchedulers.mainThread()) -> subscribe{ 在UI线程操作 }
 */
fun <T> Observable<T>.schedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}