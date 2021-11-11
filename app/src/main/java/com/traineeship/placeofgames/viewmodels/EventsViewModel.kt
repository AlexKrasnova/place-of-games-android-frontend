package com.traineeship.placeofgames.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.traineeship.placeofgames.data.event.Event
import com.traineeship.placeofgames.repository.events.EventsService
import com.traineeship.placeofgames.utils.TokenUtil
import com.traineeship.placeofgames.view.events.EventsFragment

class EventsViewModel(application: Application) : AndroidViewModel(
    application
) {

    private val eventsApiService = EventsService(TokenUtil(application.applicationContext).token!!)

    private val events: MutableLiveData<MutableList<Event>> by lazy {
        MutableLiveData<MutableList<Event>>().also {
            loadEvents()
        }
    }

    private val ownedEvents: MutableLiveData<MutableList<Event>> by lazy {
        MutableLiveData<MutableList<Event>>().also {
            loadOwnedEvents()
        }
    }

    private val participateEvents: MutableLiveData<MutableList<Event>> by lazy {
        MutableLiveData<MutableList<Event>>().also {
            loadParticipateEvents()
        }
    }

    private val updatedEvent: MutableLiveData<Event> = MutableLiveData()

    fun getEvents(): LiveData<MutableList<Event>> {
        return events
    }

    fun getOwnedEvents(): LiveData<MutableList<Event>> {
        return ownedEvents
    }

    fun getParticipateEvents(): LiveData<MutableList<Event>> {
        return participateEvents
    }

    fun getUpdatedEvent(): LiveData<Event>{
        return updatedEvent
    }

    fun deleteEvent(eventId: Int){
        eventsApiService.deleteEvent(eventId)
    }

    fun loadEvents() {
        eventsApiService.getEvents() {
            it?.let {  events.value = it }
        }
    }

    fun loadOwnedEvents(){
        eventsApiService.getOwnedEvents {
            it?.let { ownedEvents.value = it }
        }
    }

    fun loadParticipateEvents(){
        eventsApiService.getParticipateEvents {
            it?.let { participateEvents.value = it }
        }
    }

    fun decEventPeople(eventId: Int) {
        eventsApiService.decEventPeople(eventId) {
            if (it != null) {
                updatedEvent.value = it
            }
        }
    }

    fun incEventPeople(eventId: Int) {
        eventsApiService.incEventPeople(eventId) {
            it?.let { updatedEvent.value = it }
        }
    }
}
