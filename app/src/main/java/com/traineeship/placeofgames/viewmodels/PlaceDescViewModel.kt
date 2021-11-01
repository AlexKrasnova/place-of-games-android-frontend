package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.event.Place
import com.traineeship.placeofgames.repository.places.PlacesService
import com.traineeship.placeofgames.utils.TokenUtil

class PlaceDescViewModel(application: Application) : AndroidViewModel(application) {
    private val placesApiService = PlacesService(TokenUtil(application.applicationContext).token!!)

    private val place: MutableLiveData<Place> = MutableLiveData()

    fun getPlace(placeId: Int): LiveData<Place> {
        loadPlace(placeId)
        return place
    }

    private fun loadPlace(placeId: Int) {
        placesApiService.getPlace(placeId) {
            if (it != null) {
                place.value = it
            }
        }
    }
}