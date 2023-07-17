package com.trainee.appinventiv.sigmaekatra.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//http://44.202.78.173:5008/api-docs/#/OnboardingApi's/post_generateOtp
//http://44.202.78.173:5008/post_user_generateOtp
//http://44.202.78.173:5008/user/generateOtp
object RetroInstance {
    //http://44.202.78.173:5008/user/generateOtp

        val BASE_URL="http://44.202.78.173:5008/"
        fun getRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}