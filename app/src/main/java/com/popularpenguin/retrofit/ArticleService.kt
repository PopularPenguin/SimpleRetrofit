package com.popularpenguin.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {
    @GET("xyz-reader-json")
    fun articleList(): Call<List<Article>>
}
