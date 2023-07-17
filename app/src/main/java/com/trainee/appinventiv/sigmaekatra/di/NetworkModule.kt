package com.trainee.appinventiv.sigmaekatra.di

import com.trainee.appinventiv.sigmaekatra.Retrofit.RetroInstance
import com.trainee.appinventiv.sigmaekatra.Retrofit.RetroServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

class NetworkModule {


    @Singleton
    @Provides
    fun getInstance(): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(RetroInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(retrofit: Retrofit) : RetroServiceInterface
    {
        return retrofit.create(RetroServiceInterface::class.java)
    }
}
