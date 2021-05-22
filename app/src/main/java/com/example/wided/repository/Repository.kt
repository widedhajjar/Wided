package com.example.wided.repository

import com.example.wided.api.RetrofitInstance
import com.example.wided.model.Food
import retrofit2.Response

class Repository {

    suspend fun getFood(): Response<Food> {
        return RetrofitInstance.api.getFood()
    }


}