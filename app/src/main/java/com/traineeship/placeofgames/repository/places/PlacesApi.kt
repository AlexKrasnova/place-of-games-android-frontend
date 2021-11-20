package com.traineeship.placeofgames.repository.places

import com.traineeship.placeofgames.data.place.FreeTime
import com.traineeship.placeofgames.data.place.Place
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApi {

    @GET("api/v1/places")
    fun getPlaces(): Call<MutableList<Place>>

    @GET("api/v1/places/{id}")
    fun getPlace(
        @Path("id")
        placeId: Int,
    ): Call<Place>

    @GET("api/v1/places/{id}/free-time")
    fun getFreeTime(
        @Path("id")
        placeId: Int,
        @Query("date")
        date: String
    ): Call<MutableList<FreeTime>>
}