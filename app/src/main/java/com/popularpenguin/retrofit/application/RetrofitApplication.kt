package com.popularpenguin.retrofit.application

import android.app.Application
import com.popularpenguin.retrofit.dagger.AppComponent
import com.popularpenguin.retrofit.dagger.AppModule
import com.popularpenguin.retrofit.dagger.DaggerAppComponent

class RetrofitApplication : Application() {
    lateinit var retrofitComponent: AppComponent

    private fun initDagger(app: RetrofitApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()

    override fun onCreate() {
        super.onCreate()

        retrofitComponent = initDagger(this)
    }
}