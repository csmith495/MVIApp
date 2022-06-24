package com.example.mvianimals.api

import com.example.mvianimals.model.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals() : List<Animal>
}