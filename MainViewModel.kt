package com.example.mvianimals.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvianimals.api.AnimalRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repo: AnimalRepo) : ViewModel() {

    // receive the intent from the user in a channel
    // channel : a queue that can provide a stream of values between coroutines
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    // set the app in the idle state
    private val _state = MutableStateFlow<MainState>(MainState.idle)
    val state : StateFlow<MainState>
    get() = _state

    // initialize the idle state
    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { collector ->
                when(collector) {
                    is MainIntent.FetchAnimals -> fetchAnimals()

                }
            }
        }
    }

    // set app to the loading state
    // attempt to retrieve the data from the api
    private fun fetchAnimals() {
        viewModelScope.launch {
            _state.value = MainState.loading
            _state.value = try {
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}