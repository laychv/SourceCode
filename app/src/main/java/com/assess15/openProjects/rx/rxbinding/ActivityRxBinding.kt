package com.assess15.openProjects.rx.rxbinding

import android.Manifest
import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import com.assess15.comm.BaseActivity
import com.assess15.openProject.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxbinding.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * RxBinding
 * https://juejin.im/post/5afaa4726fb9a07ab458c732
 */
class ActivityRxBinding : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_rxbinding
    }

    @SuppressLint("CheckResult")
    override fun initView() {

        /**
         * 防止多次点击
         */
        val disposable = RxView.clicks(btnClick)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribe { it ->
                    toast("click")
                    Log.d("aa", "binding=======点击了按钮")
                }

        /**
         * 长按事件
         */
        val disposable1 = RxView.longClicks(btnLongClick)
                .subscribe {
                    toast("longClick")
                }

        /**
         * 监听view的选中状态
         * checkbox 选中就修改textview
         */
        val disposable2 = RxCompoundButton.checkedChanges(checkbox)
                .subscribe {
                    tvCb.text = if (it) "按钮选中了" else "按钮未选中"
                }

        /**
         * 注册登录等情况下，所有输入都合法再点亮登录按钮
         * 注册登录等情况下，所有输入都合法再点亮登录按钮
         */
        val name = RxTextView.textChanges(etName).skip(1)
        val age = RxTextView.textChanges(etAge).skip(1)

        Observable.combineLatest(name, age, BiFunction<CharSequence, CharSequence, Boolean> { _, _ ->
            val isNameEmpty = TextUtils.isEmpty(etName.text)
            val isAgeEmpty = TextUtils.isEmpty(etAge.text)
            !isNameEmpty && !isAgeEmpty
        }).subscribe { it ->
            btSubmit.isEnabled = it
            btSubmit.onClick {
                toast("click")
            }
        }

        /**
         * 动态权限
         * 添加依赖 compile 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.5@aar'
         * 记得危险权限 清单文件里也需要配置。
         * 因为各个业务组件都可能使用到危险权限，我把权限统一写在了commonLibrary里
         */
        val permissions = RxPermissions(this)
        RxView.clicks(btPermission)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(permissions.ensure(Manifest.permission.CAMERA))
                .subscribe { aBoolean ->
                    if (aBoolean!!) {
                        toast("binding=======允许")
                    } else {
                        toast("binding=======拒绝")
                    }
                }


    }


}