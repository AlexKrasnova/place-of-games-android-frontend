package com.example.placeofgames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.placeofgames.data.Event
import com.example.placeofgames.data.Place
import com.example.placeofgames.repository.EventsService
import java.util.*

class EventListViewModel : ViewModel() {

    private val eventsApiService = EventsService()

    private val events: MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>().also {
            loadEvents()
        }
    }

    fun getEvents(): LiveData<List<Event>> {
        return events
    }

    fun incEventPeople(eventId: Int){
        eventsApiService.incEventPeople(eventId)
    }

    fun loadEvents() {
        eventsApiService.getEvents {
            if (it != null) {
                events.value = it
            }
        }
    }
}