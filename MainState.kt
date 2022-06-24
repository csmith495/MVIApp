package com.example.mvianimals.view

import com.example.mvianimals.model.Animal

// class of possible states
sealed class MainState {

    // initial state of the app
    object idle: MainState()
    // state for while data is loading in the background
    object loading: MainState()

    // state where the app successfully retrieves the data from the api
    data class Animals(val animals: List<Animal>) : MainState()
    // state where the app unsuccessfully retrieves the data from the api
    data class Error(val error : String?) : MainState()
}
