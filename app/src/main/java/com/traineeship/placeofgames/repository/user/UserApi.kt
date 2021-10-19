package com.traineeship.placeofgames.repository.user

import com.traineeship.placeofgames.data.LoginData
import com.traineeship.placeofgames.data.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("api/v1/tokens")
    fun getUserToken(
        @Body loginData: LoginData
    ): Call<Token>
}