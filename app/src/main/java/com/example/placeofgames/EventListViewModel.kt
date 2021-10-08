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

    private fun loadEvents() {
//        events.value = listOf(Event(1, "Some name", Date(), 60, Place(1, "Center", "ul.Tokarey, h. 893"), 100, 5))
        eventsApiService.getEvents {
            if (it != null) {
                events.value = it
            }

        }
    }
}