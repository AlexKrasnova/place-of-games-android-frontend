package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.place.FreeTime
import com.traineeship.placeofgames.repository.places.PlacesService
import com.traineeship.placeofgames.utils.TokenUtil

class SetDateTimeVewModel(application: Application) : AndroidViewModel(application) {

    private val placesApiService = PlacesService(TokenUtil(application.applicationContext).token!!)

    private val freeTime = MutableLiveData<MutableList<FreeTime>>()

    fun getFreeTime(): LiveData<MutableList<FreeTime>> {
        return freeTime
    }

    fun loadFreeTime(placeId: Int, date: String){
        placesApiService.getFreeTime(placeId, date){
            it?.let { freeTime.value = it }
        }
    }
}