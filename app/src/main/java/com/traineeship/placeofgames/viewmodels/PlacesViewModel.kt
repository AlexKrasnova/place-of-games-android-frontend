package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.event.Place
import com.traineeship.placeofgames.repository.places.PlacesService
import com.traineeship.placeofgames.utils.TokenUtil

class PlacesViewModel(application: Application) : AndroidViewModel(application) {
    private val placesApiService = PlacesService(TokenUtil(application.applicationContext).token!!)

    private val places: MutableLiveData<MutableList<Place>> by lazy {
        MutableLiveData<MutableList<Place>>().also {
            loadPlaces()
        }
    }

    fun getPlaces(): LiveData<MutableList<Place>>{
        return places
    }

    fun loadPlaces() {
        placesApiService.getPlaces {
            if (it != null) {
                places.value = it
            }
        }
    }
}