package com.traineeship.placeofgames.repository.events

import android.util.Log
import com.traineeship.placeofgames.data.event.Event
import com.traineeship.placeofgames.data.event.NewEvent
import com.traineeship.placeofgames.repository.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsService(token: String) {

    companion object {
        const val TAG = "EVENTS_SERVICE"
    }

    private val retrofit = ServiceBuilder.buildService(token, EventsApi::class.java)

    fun getEvents(onResult: (MutableList<Event>?) -> Unit) {
        retrofit.getEvents().enqueue(
            object : Callback<MutableList<Event>> {
                override fun onFailure(call: Call<MutableList<Event>>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(
                    call: Call<MutableList<Event>>,
                    response: Response<MutableList<Event>>
                ) {
                    Log.d(TAG, "events ok")
                    Log.d(TAG, "onResponse: " + response.code())
                    val events = response.body()
                    Log.d(TAG, events.toString())
                    onResult(events)
                }
            }
        )
    }

    fun getOwnedEvents(onResult: (MutableList<Event>?) -> Unit) {
        retrofit.getOwnedEvents().enqueue(
            object : Callback<MutableList<Event>> {
                override fun onFailure(call: Call<MutableList<Event>>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(
                    call: Call<MutableList<Event>>,
                    response: Response<MutableList<Event>>
                ) {
                    Log.d(TAG, "owned events ok")
                    Log.d(TAG, "onResponse: " + response.code())
                    val events = response.body()
                    Log.d(TAG, events.toString())
                    onResult(events)
                }
            }
        )
    }

    fun getParticipateEvents(onResult: (MutableList<Event>?) -> Unit){
        retrofit.getParticipateEvents().enqueue(
            object : Callback<MutableList<Event>> {
                override fun onFailure(call: Call<MutableList<Event>>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(
                    call: Call<MutableList<Event>>,
                    response: Response<MutableList<Event>>
                ) {
                    Log.d(TAG, "participate events ok")
                    Log.d(TAG, "onResponse: " + response.code())
                    val events = response.body()
                    Log.d(TAG, events.toString())
                    onResult(events)
                }
            }
        )
    }

    fun getEvent(eventId: Int, onResult: (Event?) -> Unit){
        retrofit.getEvent(eventId).enqueue(
            object : Callback<Event> {
                override fun onFailure(call: Call<Event>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(
                    call: Call<Event>,
                    response: Response<Event>
                ) {
                    Log.d(TAG, "response ok")
                    val event = response.body()
                    Log.d(TAG, event.toString())
                    onResult(event)
                }
            }
        )
    }

    fun incEventPeople(eventId: Int, onResult: (Event?) -> Unit){
        retrofit.addParticipant(eventId).enqueue(
            object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "inc ok")

                    getEvent(eventId) {
                        onResult(it)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, t.message!!)
                }


            }
        )
    }

    fun decEventPeople(eventId: Int, onResult: (Event?) -> Unit) {
        retrofit.deleteParticipant(eventId).enqueue(
            object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "dec ok")

                    getEvent(eventId) {
                        onResult(it)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, t.message!!)
                }
            }
        )
    }

    fun deleteEvent(eventId: Int) {
        retrofit.deleteEvent(eventId).enqueue(
            object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "onResponse: ok")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    //todo
                }
            }
        )
    }

    fun createEvent(newEvent: NewEvent, onResult: (Boolean) -> Unit){
        retrofit.createEvent(newEvent).enqueue(
            object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "onResponse: event created")
                    onResult(true)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onResult(false)
                }

            }
        )
    }
}
