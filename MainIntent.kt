package com.example.mvianimals.view

sealed class MainIntent {

    // the user interaction representation
    // when the user clicks the button to retrieve animal data
    // declared as an object for abstraction for the user interaction
    object FetchAnimals: MainIntent()
}