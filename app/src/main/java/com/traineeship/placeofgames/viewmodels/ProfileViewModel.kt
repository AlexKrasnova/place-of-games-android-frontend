package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.event.Place
import com.traineeship.placeofgames.data.user.User
import com.traineeship.placeofgames.repository.places.PlacesService
import com.traineeship.placeofgames.repository.user.UserService
import com.traineeship.placeofgames.utils.TokenUtil

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val profileApiService = UserService(TokenUtil(application.applicationContext).token!!)

    private val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            loadUser()
        }
    }

    fun getUser(): LiveData<User>{
        return user
    }

    fun loadUser(){
        profileApiService.getUser {
            it?.let { user.value = it }
        }
    }

}