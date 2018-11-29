package cn.ju.comm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var tags: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initView()
        tags = javaClass.simpleName
    }

    abstract fun getLayoutRes(): Int
    abstract fun initView()

}