package com.popularpenguin.retrofit.dagger

import com.popularpenguin.retrofit.ViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides @Singleton
    fun provideViewModel(retrofit: Retrofit): ViewModel = ViewModel(retrofit)
}