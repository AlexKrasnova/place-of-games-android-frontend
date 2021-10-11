package com.example.placeofgames.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.placeofgames.data.Event
import com.example.placeofgames.repository.EventsService

class EventListViewModel : ViewModel() {

    private val eventsApiService = EventsService()

    private val events: MutableLiveData<MutableList<Event>> by lazy {
        MutableLiveData<MutableList<Event>>().also {
            loadEvents()
        }
    }

    fun getEvents(): LiveData<MutableList<Event>> {
        return events
    }

    fun incEventPeople(eventId: Int){
        eventsApiService.incEventPeople(eventId) {
            val changedEvent = events.value?.indexOf(events.value?.find { it.id == eventId })
            if (changedEvent != null) {
                val list = events.value
                list?.set(changedEvent, it!!)
                events.value = list
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