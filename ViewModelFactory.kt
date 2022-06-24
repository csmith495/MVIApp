package com.example.mvianimals.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvianimals.api.AnimalApi
import com.example.mvianimals.api.AnimalRepo

class ViewModelFactory(private val api: AnimalApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(AnimalRepo(api)) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }
}