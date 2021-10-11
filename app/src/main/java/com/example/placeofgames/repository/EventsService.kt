package com.example.placeofgames.repository

import android.util.Log
import com.example.placeofgames.data.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class EventsService {

    private val LOG = "EVENTS_SERVICE"
    private val retrofit = EventsServiceBuilder.buildService(EventsApi::class.java)

    fun getEvents(onResult: (MutableList<Event>?) -> Unit) {
        retrofit.getEvents().enqueue(
            object : Callback<MutableList<Event>> {
                override fun onFailure(call: Call<MutableList<Event>>, t: Throwable) {
                    onResult(null)
                    Log.d(LOG, t.message!!)
                }

                override fun onResponse(
                    call: Call<MutableList<Event>>,
                    response: Response<MutableList<Event>>
                ) {
                    Log.d(LOG, "response ok")
                    val events = response.body()
                    Log.d(LOG, events.toString())
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
                    Log.d(LOG, t.message!!)
                }

                override fun onResponse(
                    call: Call<Event>,
                    response: Response<Event>
                ) {
                    Log.d(LOG, "response ok")
                    val event = response.body()
                    Log.d(LOG, event.toString())
                    onResult(event)
                }
            }
        )
    }

    fun incEventPeople(eventId: Int){
        retrofit.addParticipant(eventId).enqueue(
            object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(LOG, "inc ok")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(LOG, t.message!!)
                }


            }
        )
    }
}
