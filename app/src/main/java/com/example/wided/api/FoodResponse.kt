package com.example.wided.api


import com.example.wided.model.Food

data class FoodResponse(
    val name: String,
    val totalResults : Int,
    val results : List<Food>


)