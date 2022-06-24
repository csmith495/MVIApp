package com.example.mvianimals.api

class AnimalRepo(private val api: AnimalApi) {

    suspend fun getAnimals() = api.getAnimals()
}