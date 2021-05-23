package com.example.wided

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wided.model.Food
import com.example.wided.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {
//MVVM
    val myResponse: MutableLiveData<Response<Food>> = MutableLiveData()

    fun getFood(){
        viewModelScope.launch {
            val response=repository.getFood()
            myResponse.value = response


        }
    }
}