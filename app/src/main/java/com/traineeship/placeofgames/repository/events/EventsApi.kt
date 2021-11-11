package com.traineeship.placeofgames.repository.events

import com.traineeship.placeofgames.data.event.Event
import retrofit2.Call
import retrofit2.http.*

interface EventsApi {

    @GET("api/v1/events")
    fun getEvents(): Call<MutableList<Event>>

    @GET("api/v1/user/events-to-participate")
    fun getParticipateEvents(): Call<MutableList<Event>>

    @GET("api/v1/user/owned-events")
    fun getOwnedEvents(): Call<MutableList<Event>>

    @POST("api/v1/events/{id}/participants")
    fun addParticipant(
        @Path("id")
        eventId: Int,
    ): Call<Void>

    @GET("api/v1/events/{id}")
    fun getEvent(
        @Path("id")
        eventId: Int,
    ): Call<Event>

    @DELETE("/api/v1/events/{id}/participants")
    fun deleteParticipant(
        @Path("id")
        eventId: Int,
    ): Call<Void>

    @DELETE("api/v1/events/{id}")
    fun deleteEvent(
        @Path("id")
        eventId: Int
    ): Call<Void>
}