package com.daurulang.jobinformation.`interface`

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dev6.dansmultipro.com/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createService(serviceClass: Class<RetrofitService>): RetrofitService {
        return retrofit.create(serviceClass)
    }
}