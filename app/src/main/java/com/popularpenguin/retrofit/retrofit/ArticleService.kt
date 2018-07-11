package com.popularpenguin.retrofit.retrofit

import com.popularpenguin.retrofit.model.Article
import io.reactivex.Observable
import retrofit2.http.GET

interface ArticleService {
    @GET("xyz-reader-json")
    fun articleList(): Observable<List<Article>>
}
