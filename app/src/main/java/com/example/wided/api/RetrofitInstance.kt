package com.example.wided.api

import com.example.wided.model.Food
import com.example.wided.util.Constans.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }
    val api: FoodApi by lazy {
        retrofit.create(FoodApi::class.java)
    }


} 