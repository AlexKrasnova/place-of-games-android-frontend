package com.traineeship.placeofgames.repository.user

import com.traineeship.placeofgames.data.user.LoginData
import com.traineeship.placeofgames.data.user.Token
import com.traineeship.placeofgames.data.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("api/v1/tokens")
    fun getUserToken(
        @Body loginData: LoginData
    ): Call<Token>

    @GET("api/v1/user")
    fun getUser(): Call<User>

    @POST("/api/v1/users")
    fun createUser(
        @Body user: User
    ): Call<Void>
}