package com.traineeship.placeofgames.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.traineeship.placeofgames.data.Event
import com.traineeship.placeofgames.repository.EventsService

class EventListViewModel : ViewModel() {

    private val eventsApiService = EventsService()

    private val events: MutableLiveData<MutableList<Event>> by lazy {
        MutableLiveData<MutableList<Event>>().also {
            loadEvents()
        }
    }

    private val updatedEvent: MutableLiveData<Event> = MutableLiveData()

    fun getEvents(): LiveData<MutableList<Event>> {
        return events
    }

    fun getUpdatedEvent(): LiveData<Event>{
        return updatedEvent
    }

    fun incEventPeople(eventId: Int){
        eventsApiService.incEventPeople(eventId) {
            if (it != null){
                updatedEvent.value = it
            }
        }
    }

    fun loadEvents() {
        eventsApiService.getEvents {
            if (it != null) {
                events.value = it
            }
        }
    }
}