package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.traineeship.placeofgames.data.event.Event
import com.traineeship.placeofgames.repository.events.EventsService
import com.traineeship.placeofgames.utils.TokenUtil

class EventListViewModel(application: Application) : AndroidViewModel(
    application
) {

    private val eventsApiService = EventsService(TokenUtil(application.applicationContext).token!!)

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
        eventsApiService.getEvents() {
            if (it != null) {
                events.value = it
            }
        }
    }

    fun decEventPeople(eventId: Int) {
        eventsApiService.decEventPeople(eventId) {
            if (it != null){
                updatedEvent.value = it
            }
        }
    }

}
