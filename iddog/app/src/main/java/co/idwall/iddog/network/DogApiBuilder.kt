package co.idwall.iddog.network

import co.idwall.iddog.api.ApiDogRequest
import co.idwall.iddog.api.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogApiBuilder {

    fun getApiInstance(): ApiDogRequest {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDogRequest::class.java)
    }
}
