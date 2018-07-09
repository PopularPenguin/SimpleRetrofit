package com.popularpenguin.retrofit

import io.reactivex.Observable
import retrofit2.http.GET

interface ArticleService {
    @GET("xyz-reader-json")
    fun articleList(): Observable<List<Article>>
}
