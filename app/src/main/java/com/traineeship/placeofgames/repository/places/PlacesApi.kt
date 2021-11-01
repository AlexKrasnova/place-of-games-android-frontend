package com.traineeship.placeofgames.repository.places

import com.traineeship.placeofgames.data.event.Place
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlacesApi {

    @GET("api/v1/places")
    fun getPlaces(): Call<MutableList<Place>>

    @GET("api/v1/places/{id}")
    fun getPlace(
        @Path("id")
        placeId: Int,
    ): Call<Place>
}