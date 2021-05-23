package com.example.wided

import android.app.Application
import android.content.Context

class FoodApplication : Application() {
    companion object{
        var  context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context=this

    }

}
