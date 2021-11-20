package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.event.NewEvent
import com.traineeship.placeofgames.repository.events.EventsService
import com.traineeship.placeofgames.utils.TokenUtil

class CreateEventViewModel(application: Application) : AndroidViewModel(application) {

    private val eventsApiService = EventsService(TokenUtil(application.applicationContext).token!!)

    private val eventCreated = MutableLiveData<Boolean>()

    private val selectedDate = MutableLiveData<String>()
    private val selectedTime = MutableLiveData<String>()
    private var duration: Int = 0

    var placeId: Int? = null
        set(value) {
            value?.let { field = it }
        }

    fun setSelectedDate(date: String){
        selectedDate.value = date
    }

    fun setSelectedTime(time: String){
        selectedTime.value = time
    }

    fun getSelectedDate(): LiveData<String> = selectedDate
    fun getSelectedTime(): LiveData<String> = selectedTime

    fun setDuration(duration: Int){
        this.duration = duration
    }

    fun getDuration() = duration

    fun createEvent(newEvent: NewEvent){
        eventsApiService.createEvent(newEvent){
            eventCreated.value = it
        }
    }

    fun getEventCreateCallback(): LiveData<Boolean> = eventCreated

}