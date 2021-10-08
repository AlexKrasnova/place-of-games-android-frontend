package com.example.placeofgames.repository

import com.example.placeofgames.data.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApi {
    @GET("api/v1/events")
    fun getEvents(): Call<MutableList<Event>>

    @POST("api/v1/events/{id}/participants")
    fun addParticipant(
        @Path("id")
        eventId: Int
    ): Call<Event>
}