package cn.ju.sourcecode.retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.ju.sourcecode.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        initClick()
    }

    private fun initClick() {
        btnRetrofit.setOnClickListener {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(GitHubService::class.java)
            val repos = service.listRepos("octocat")
            repos.enqueue(object : retrofit2.Callback<List<Repo>> {
                override fun onFailure(call: retrofit2.Call<List<Repo>>?, t: Throwable?) {
                }

                override fun onResponse(call: retrofit2.Call<List<Repo>>?, response: retrofit2.Response<List<Repo>>?) {
                    tvRetrofit.text = response.toString()
                }
            })
        }

    }
}