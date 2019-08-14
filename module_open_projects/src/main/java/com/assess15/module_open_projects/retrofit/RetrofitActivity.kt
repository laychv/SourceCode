package com.assess15.module_open_projects.retrofit

import com.assess15.module_base.ui.BaseActivity
import com.assess15.module_open_projects.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_retrofit
    }

    override fun initView() {
        initClick()
    }

    private fun initClick() {
        btnRetrofit.setOnClickListener {
            initNet()
        }
    }

    private fun initNet() {
        // 4.
        val retrofit = Retrofit.Builder()
                .client(OkHttpClient())// 默认使用OkHttp
                .baseUrl("https://api.github.com/")//设置网络url请求地址
//                    .addConverterFactory(GsonConverterFactory.create())// 设置Gson数据解析器
                .addConverterFactory(MoshiConverterFactory.create())// 设置Moshi数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 支持RxJava平台
                .build()
        // 5.
        val service = retrofit.create(GitHubService::class.java)// 动态代理模式
        val repos = service.listRepos("octocat")
        // 6.
        repos.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                // 7.
                val body = response?.body()
                body?.forEach {
                    tvRetrofit.text = it.blobs_url
                }
//                tvRetrofit.text = response.toString()
            }
        })
    }
}