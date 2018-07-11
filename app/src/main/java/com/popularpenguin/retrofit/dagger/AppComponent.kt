package com.popularpenguin.retrofit.dagger

import com.popularpenguin.retrofit.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
}