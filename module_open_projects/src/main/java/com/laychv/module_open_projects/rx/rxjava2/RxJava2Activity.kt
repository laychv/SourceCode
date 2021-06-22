package com.laychv.module_open_projects.rx.rxjava2

import android.os.SystemClock
import android.util.Log
import com.laychv.module_base.ui.BaseActivity
import com.laychv.module_open_projects.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava.*
import java.util.concurrent.TimeUnit

class RxJava2Activity : BaseActivity() {

    private var disposable: Disposable? = null

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

        /**
         * intervalRange
         */
        btnIntervalRange.setOnClickListener {
            testIntervalRange()
            /**
             * 获取 对内存中的大小
             */
            val rt = Runtime.getRuntime()
            val maxMemory = rt.maxMemory()
            val ss = maxMemory / 1024 / 1024
            btnIntervalRange.text = "$ss MB"
        }
        /**
         * interval
         */
        btnInterval.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                interval()
            } else {
//                Schedulers.shutdown()
            }
        }
    }

    /**
     * interval
     */
    private fun interval() {
        var sub = Observable.interval(1, TimeUnit.SECONDS)
//                .subscribe(object : Observer<Long> {
//                    override fun onComplete() {
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        disposable = d
//                    }
//
//                    override fun onNext(t: Long) {
//                        Log.d(tags, "" + t)
//                    }
//
//                    override fun onError(e: Throwable) {
//                    }
//
//                })
    }

    /**
     * intervalRange
     */
    private fun testIntervalRange() {
//        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(object : Observer<Long> {
//                    override fun onSubscribe(d: Disposable) {
//                    }
//
//                    override fun onNext(t: Long) {
//                        tv.text = t.toString()
//                        btn.isEnabled = false
//                        if (t.toInt() == 9) {
//                            btn.isEnabled = true
//                        }
//                    }
//
//                    override fun onError(e: Throwable) {
//                    }
//
//                    override fun onComplete() {
//                    }
//                })

//        Observable.intervalRange(1, 20, 0, 1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Consumer<Long> {
//                    override fun accept(t: Long?) {
//                        tv.text = t.toString()
//                    }
//
//                })

        Observable.intervalRange(1, 500, 0, 1, TimeUnit.SECONDS)
//                .take(30)
//                .doOnNext {
//                    Log.d("t","测试${it}")
//                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Long) {
//                        val t = t % 10
//                        when {
//                            t.toInt() == 0 -> {
//                                Log.d("t", "10")
//                            }
//                            t.toInt() <= 5 -> {
//                                Log.d("t", "5")
//                            }
//                            t.toInt() <= 7 -> {
//                                Log.d("t", "7")
//                            }
//                            t.toInt() <= 9 -> {
//                                Log.d("t", "9")
//                            }
//                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * 创建
     */
    private fun initObservable() {
        // 第一步：创建被观察者
        // 1.create方式创建
        val observable = Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("hello")
            emitter.onNext("world")
            emitter.onComplete()
        })
        // 2.just方式创建
        val just = Observable.just("hello", "world")
        // 3.fromArray方式创建
        val from = Observable.fromArray(arrayOf("hello", "world"))

        //第二步：创建观察者
        val observer = object : Observer<String> {
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

        // 第三步:订阅 被观察者 订阅 观察者
        observable.subscribe(observer)
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
        }).subscribeOn(Schedulers.io()) // 这里第一次设置有效果，以后的无效
                //map操作符，Function<Object,Object>，只要类型为Object的子类就可以进行转换
                .map { it ->
                    //这个就是转换的函数，返回的是转换结果
                    "This is a String Type:$it"
                }
                // 设置被观察者所在线程
                .subscribeOn(Schedulers.io()) // 再次设置无效
                // 设置观察者所在线程
                .observeOn(AndroidSchedulers.mainThread()) // 可以被调用多次，每次都生效
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