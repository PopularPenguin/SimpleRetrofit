package com.popularpenguin.retrofit.retrofit

import com.popularpenguin.retrofit.model.Article
import io.reactivex.Single
import retrofit2.http.GET

interface ArticleService {
    @GET("xyz-reader-json")
    fun articleList(): Single<List<Article>>
}
