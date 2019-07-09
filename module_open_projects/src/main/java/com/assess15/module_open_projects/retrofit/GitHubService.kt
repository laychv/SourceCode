package com.assess15.module_open_projects.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos") // {} 表示动态的地址
    fun listRepos(@Path("user") user: String): Call<List<Repo>>// 默认参数 如果user为空，默认传入@Path中的参数
}