package com.popularpenguin.retrofit.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var app: Application) {
    @Provides @Singleton
    fun provideContext(): Context = app
}