package com.example.wided

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wided.repository.Repository

class MainViewModelFactory ( private val repository: Repository
): ViewModelProvider.Factory{
    override fun <T: ViewModel?> create(mocelClass: Class<T>): T{
        return MainViewModel(repository) as T
    }

}