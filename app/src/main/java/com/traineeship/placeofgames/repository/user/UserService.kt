package com.traineeship.placeofgames.repository.user

import android.util.Log
import com.traineeship.placeofgames.data.user.LoginData
import com.traineeship.placeofgames.data.user.Token
import com.traineeship.placeofgames.data.user.User
import com.traineeship.placeofgames.repository.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService {

    companion object {
        const val TAG = "USER_SERVICE"
    }

    private val retrofit = ServiceBuilder.buildService(UserApi::class.java)

    fun getToken(loginData: LoginData, onResult: (Token?) -> Unit) {
        retrofit.getUserToken(loginData).enqueue(
            object : Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d(TAG, "get token ok")
                    val token = response.body()
                    Log.d(TAG, token.toString())
                    onResult(token)
                }
            }
        )
    }

    fun createUser(user: User, onResult: (Token?) -> Unit){
        retrofit.createUser(user).enqueue(
            object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "onResponse: create ok")
                    getToken(LoginData(user.login, user.password)){
                        onResult(it)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "create fail")
                    onResult(null)
                }

            }
        )
    }
}