package com.laychv.module_arch.aop.login

import android.content.Intent
import android.util.Log
import android.view.View
import com.laychv.module_aop.loginAspect.annotation.ClickBehavior
import com.laychv.module_aop.loginAspect.annotation.LoginCheck
import com.laychv.module_arch.R
import com.laychv.module_base.ui.BaseActivity

class LoginActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_login_asp
    }

    override fun initView() {
    }

    // 登录点击事件（用户行为统计）
    @ClickBehavior("登录")
    fun login(view: View) {
        Log.e(TAG, "模拟接口请求……验证通过，登录成功！")
    }

    // 用户行为统计（友盟统计？！后台要求自己统计）
    @ClickBehavior("我的专区")
    @LoginCheck
    fun area(view: View) {
        Log.e(TAG, "开始跳转到 -> 我的专区 Activity")
        startActivity(Intent(this, OtherActivity::class.java))
    }

    // 用户行为统计
    @ClickBehavior("我的优惠券")
    @LoginCheck
    fun coupon(view: View) {
        Log.e(TAG, "开始跳转到 -> 我的优惠券 Activity")
        startActivity(Intent(this, OtherActivity::class.java))
    }

    // 用户行为统计
    @ClickBehavior("我的积分")
    @LoginCheck
    fun score(view: View) {
        Log.e(TAG, "开始跳转到 -> 我的积分 Activity")
        startActivity(Intent(this, OtherActivity::class.java))
    }

    companion object {
        private val TAG = "login"
    }
}
