package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.traineeship.placeofgames.data.event.Event
import com.traineeship.placeofgames.repository.events.EventsService
import com.traineeship.placeofgames.utils.TokenUtil

class EventDescViewModel(application: Application) : AndroidViewModel(application) {

    private val eventsApiService = EventsService(TokenUtil(application.applicationContext).token!!)

    private val event: MutableLiveData<Event> = MutableLiveData()

    fun getEvent(eventId: Int): LiveData<Event> {
        loadEvent(eventId)
        return event
    }

    fun incEventPeople(eventId: Int){
        eventsApiService.incEventPeople(eventId) {
            event.value = it
        }
    }

    fun decEventPeople(eventId: Int){
        eventsApiService.decEventPeople(eventId) {
            event.value = it
        }
    }

    fun deleteEvent(eventId: Int){
        eventsApiService.deleteEvent(eventId)
    }

    private fun loadEvent(eventId: Int) {
        eventsApiService.getEvent(eventId) {
            if (it != null) {
                event.value = it
            }
        }
    }
}