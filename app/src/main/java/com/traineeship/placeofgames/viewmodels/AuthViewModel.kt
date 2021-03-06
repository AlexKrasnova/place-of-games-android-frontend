package com.traineeship.placeofgames.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.traineeship.placeofgames.data.user.LoginData
import com.traineeship.placeofgames.data.user.Token
import com.traineeship.placeofgames.data.user.User
import com.traineeship.placeofgames.repository.user.UserService

class AuthViewModel : ViewModel() {
    private val userApiService = UserService(null)

    private val token: MutableLiveData<Token> = MutableLiveData()

    fun getTokenLiveData(loginData: LoginData): LiveData<Token> {
        loadToken(loginData)
        return token
    }

    fun getTokenSignUp(): LiveData<Token>{
        return token
    }

    private fun loadToken(loginData: LoginData) {
        userApiService.getToken(loginData) {
            if (it != null) {
                token.value = it
            }
        }
    }

    fun registerUser(user: User){
        userApiService.createUser(user){
            if (it != null){
                token.value = it
            }
        }
    }
}