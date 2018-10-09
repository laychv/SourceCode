package cn.ju.sc.rx.rxbinding

import android.os.Bundle
import android.util.Log
import cn.ju.comm.BaseActivity
import cn.ju.sc.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxbinding.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class ActivityRxBinding : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxbinding)
        initView()
    }

    /**
     * 防止多次点击
     */
    private fun initView() {
        val disposable = RxView.clicks(btnClick)
                .throttleFirst(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribe { _ ->
                    toast("click")
                    Log.d("aa", "binding=======点击了按钮")
                }

        val disposable1 = RxView.longClicks(btnClick)
                .subscribe {
                    toast("longClick")
                }
    }


}