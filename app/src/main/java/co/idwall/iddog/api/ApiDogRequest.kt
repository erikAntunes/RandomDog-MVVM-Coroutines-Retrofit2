package co.idwall.iddog.api

import retrofit2.http.GET

const val BASE_URL = "https://random.dog"

interface ApiDogRequest {

    @GET("/woof.json?ref=apilist.fun")
    suspend fun getRandomDog(): RandomDogResponse

}