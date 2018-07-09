package com.popularpenguin.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GerritAPI {
    @GET("changes/")
    fun loadChanges(@Query("q") status: String): Call<List<Change>>
}