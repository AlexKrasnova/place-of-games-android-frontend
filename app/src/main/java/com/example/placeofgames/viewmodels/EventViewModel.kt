package com.example.placeofgames.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.placeofgames.data.Event
import com.example.placeofgames.repository.EventsService

class EventViewModel : ViewModel() {
    private val eventsApiService = EventsService()

    private val event: MutableLiveData<Event> = MutableLiveData()

    fun getEventLiveData(eventId: Int): LiveData<Event> {
        loadEvent(eventId)
        return event
    }

    fun incEventPeople(eventId: Int){
        eventsApiService.incEventPeople(eventId)
    }

    private fun loadEvent(eventId: Int) {
        eventsApiService.getEvent(eventId) {
            if (it != null) {
                event.value = it
            }
        }
    }
}