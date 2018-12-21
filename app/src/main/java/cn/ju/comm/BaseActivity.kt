package cn.ju.comm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

abstract class BaseActivity : AppCompatActivity() {

    var tags: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        Logger.addLogAdapter(AndroidLogAdapter())
        initView()
        tags = javaClass.simpleName
    }

    abstract fun getLayoutRes(): Int
    abstract fun initView()

}