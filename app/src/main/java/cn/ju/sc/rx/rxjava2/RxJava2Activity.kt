package cn.ju.sc.rx.rxjava2

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import cn.ju.sc.R
import cn.ju.comm.BaseActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.ObservableEmitter
import io.reactivex.annotations.NonNull

class RxJava2Activity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_rxjava
    }

    override fun initView() {
        initObservable()
        chainObservable()

        initMap()
        // 线程控制
        doThreadWithScheduler()
        //
        chainObservableWithScheduler()
    }

    /**
     *
     */
    private fun initObservable() {
        // 第一步：创建被观察者
        // 1.create方式创建
        val create = Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("hello")
            emitter.onNext("world")
            emitter.onComplete()
        })
        // 2.just方式创建
        val just = Observable.just("hello", "world")
        // 3.from方式创建
        val from = Observable.fromArray(arrayOf("hello", "world"))

        //第二步：创建观察者
        val value = object : Observer<String> {
            override fun onComplete() {
                Log.d(tags, "完成")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d(tags, t)
            }

            override fun onError(e: Throwable) {
            }
        }

        // 第三步:订阅
        create.subscribe(value)
    }

    /**
     * 流式写法
     */
    private fun chainObservable() {
        Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("")
            emitter.onNext("")
            emitter.onComplete()
        }).subscribe(object : Observer<String> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
            }

            override fun onError(e: Throwable) {
            }

        })
    }

    /**
     * map
     */
    private fun initMap() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            var i = 0

            @Throws(Exception::class)
            override fun subscribe(@NonNull e: ObservableEmitter<Int>) {
                while (i < 3) {
                    Log.d(tags, "Observable on " + Thread.currentThread().name + " emit " + i)
                    e.onNext(i)
                    i++
                }
            }
        }).subscribeOn(Schedulers.io())
                //map操作符，Function<Object,Object>，只要类型为Object的子类就可以进行转换
                .map { it ->
                    //这个就是转换的函数，返回的是转换结果
                    "This is a String Type:$it"
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(s: String) {
                        //这里接收的就是一个String类型了
                        Log.d(tags, "Observer on " + Thread.currentThread().name + " Receive:" + s)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    private fun doThreadWithScheduler() {
        Observable.just("小1", "小2", "小3").schedulers().subscribe {
            Log.d(tags, it)
        }
    }

    private fun chainObservableWithScheduler() {
        Observable.create<String> { emitter ->
            SystemClock.sleep(2000)
            emitter.onNext("aaa")
            SystemClock.sleep(3000)
            emitter.onNext("bbb")
            SystemClock.sleep(5000)
            emitter.onComplete()
        }.schedulers().subscribe(object : Observer<String> {
            override fun onComplete() {
                Log.d(tags, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d(tags, t)
            }

            override fun onError(e: Throwable) {
            }
        })

    }

}