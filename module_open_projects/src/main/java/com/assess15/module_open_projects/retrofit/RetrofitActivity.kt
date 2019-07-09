package com.assess15.module_open_projects.retrofit

import com.assess15.module_base.ui.BaseActivity
import com.assess15.module_open_projects.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_retrofit
    }

    override fun initView() {
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