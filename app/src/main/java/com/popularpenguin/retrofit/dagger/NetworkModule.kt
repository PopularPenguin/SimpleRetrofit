package com.popularpenguin.retrofit.dagger

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "https://go.udacity.com/"
    }

    @Provides @Named(BASE_URL)
    fun provideBaseUrlString() = BASE_URL

    val gson = GsonBuilder().setLenient().create()

    @Provides @Singleton
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}