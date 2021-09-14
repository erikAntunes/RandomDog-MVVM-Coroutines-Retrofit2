package co.idwall.iddog.data

import co.idwall.iddog.domain.LoginBody
import co.idwall.iddog.domain.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IddogEndpoints {

    //BASE_URL: https://iddog-nrizncxqba-uc.a.run.app/

    @POST("signup")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse


    //TODO implement a GET method for https://iddog-nrizncxqba-uc.a.run.app/feed with a Authorization header
    // with the value of LoginResponse.User.token and a Query param 'category' with one of these four values:
    // ['husky', 'hound', 'pug', 'labrador'] that returns a DogFeed object
}