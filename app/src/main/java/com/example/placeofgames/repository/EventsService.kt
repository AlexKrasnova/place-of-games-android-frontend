package com.example.placeofgames.repository

import android.util.Log
import com.example.placeofgames.data.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsService {

    private val LOG = "EVENTS_SERVICE"

    fun getEvents(onResult: (MutableList<Event>?) -> Unit) {
        val retrofit = EventsServiceBuilder.buildService(EventsApi::class.java)
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
                    onResult(events)
                }
            }
        )
    }
}