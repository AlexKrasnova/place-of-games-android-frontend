package com.traineeship.placeofgames.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.traineeship.placeofgames.data.Event
import com.traineeship.placeofgames.data.Token
import com.traineeship.placeofgames.repository.events.EventsService
import com.traineeship.placeofgames.repository.events.EventsService.Companion.TAG
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

}

//class EventListViewModelFactory(private val token: Token) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return modelClass.getConstructor(EventListViewModel::class.java).newInstance(token)
//    }
//}