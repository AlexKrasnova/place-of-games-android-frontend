package com.traineeship.placeofgames.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private var token: String? = null

    private val client = OkHttpClient.Builder().addInterceptor {
        val request = it.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $token")
            .build()
        return@addInterceptor it.proceed(request)
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(token: String, service: Class<T>): T {
        this.token = token
        return retrofit.create(service)
    }

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}