package com.example.wided.api

import com.example.wided.model.Food
import retrofit2.Response

import retrofit2.http.GET




interface FoodApi {
    @GET("/food/search?query=apple&number=2")
    suspend fun getFood() : Response<Food>


}