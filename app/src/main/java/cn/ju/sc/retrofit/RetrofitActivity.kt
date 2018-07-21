package cn.ju.sc.retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.ju.sc.R
import cn.ju.sc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        initClick()
    }

    private fun initClick() {
        btnRetrofit.setOnClickListener {
            // 4.
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")//设置网络url请求地址
                    .addConverterFactory(GsonConverterFactory.create())// 设置数据解析器
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 支持RxJava平台
                    .build()

            // 5.
            val service = retrofit.create(GitHubService::class.java)
            val repos = service.listRepos("octocat")
            // 6.
            repos.enqueue(object : retrofit2.Callback<List<Repo>> {
                override fun onFailure(call: retrofit2.Call<List<Repo>>?, t: Throwable?) {
                }

                override fun onResponse(call: retrofit2.Call<List<Repo>>?, response: retrofit2.Response<List<Repo>>?) {
                    // 7.
                    tvRetrofit.text = response.toString()
                }
            })

        }

    }
}