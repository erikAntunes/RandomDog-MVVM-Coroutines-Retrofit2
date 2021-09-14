package co.idwall.iddog.api

import co.idwall.iddog.network.DogApiBuilder

class DogRepository {

    private val apiIdDog = DogApiBuilder.getApiInstance()

    suspend fun getRandomDog(): RandomDogResponse {

        return apiIdDog.getRandomDog()
    }
}