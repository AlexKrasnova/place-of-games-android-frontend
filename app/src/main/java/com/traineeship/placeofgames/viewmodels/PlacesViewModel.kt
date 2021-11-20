package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.place.Place
import com.traineeship.placeofgames.repository.places.PlacesService
import com.traineeship.placeofgames.utils.TokenUtil

class PlacesViewModel(application: Application) : AndroidViewModel(application) {
    private val placesApiService = PlacesService(TokenUtil(application.applicationContext).token!!)

    private val places: MutableLiveData<MutableList<Place>> by lazy {
        MutableLiveData<MutableList<Place>>().also {
            loadPlaces()
        }
    }

    private val autoCompletePlaces: MutableLiveData<MutableList<Place>> by lazy {
        MutableLiveData<MutableList<Place>>().also {
            loadAutoCompletePlaces()
        }
    }

    fun getAutoCompletePlaces(): LiveData<MutableList<Place>> {
        return autoCompletePlaces
    }

    fun getPlaces(): LiveData<MutableList<Place>>{
        return places
    }

    private fun loadAutoCompletePlaces() {
        placesApiService.getPlaces {
            it?.let { autoCompletePlaces.value = it }
        }
    }

    fun loadPlaces() {
        placesApiService.getPlaces {
            if (it != null) {
                places.value = it
            }
        }
    }
}