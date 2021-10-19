package com.traineeship.placeofgames.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.traineeship.placeofgames.data.LoginData
import com.traineeship.placeofgames.data.Token
import com.traineeship.placeofgames.repository.user.UserService

class AuthViewModel : ViewModel() {
    private val userApiService = UserService()

    private val token: MutableLiveData<Token> = MutableLiveData()

    fun getTokenLiveData(loginData: LoginData): LiveData<Token> {
        loadToken(loginData)
        return token
    }

    private fun loadToken(loginData: LoginData) {
        userApiService.getToken(loginData) {
            if (it != null) {
                token.value = it
            }
        }
    }
}