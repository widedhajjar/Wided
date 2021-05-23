package com.example.wided.api

import com.example.wided.FoodApplication.Companion.context
import com.example.wided.model.Food
import com.example.wided.util.Constans.Companion.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object RetrofitInstance {



            var cache: Cache = Cache(File(context?.cacheDir,"responses"), 10 * 1024 * 1024) //10MiB
            var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    }
    val api: FoodApi by lazy {
        retrofit.create(FoodApi::class.java)
    }


} 