package com.traineeship.placeofgames.repository.places

import android.util.Log
import com.traineeship.placeofgames.data.place.FreeTime
import com.traineeship.placeofgames.data.place.Place
import com.traineeship.placeofgames.repository.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlacesService(token: String) {
    companion object {
        const val TAG = "PLACES_SERVICE"
    }

    private val retrofit = ServiceBuilder.buildService(token, PlacesApi::class.java)

    fun getPlaces(onResult: (MutableList<Place>?) -> Unit) {
        retrofit.getPlaces().enqueue(
            object : Callback<MutableList<Place>> {
                override fun onResponse(
                    call: Call<MutableList<Place>>,
                    response: Response<MutableList<Place>>
                ) {
                    Log.d(TAG, "onResponse: ok")
                    val places = response.body()
                    Log.d(TAG, places.toString())
                    onResult(places)
                }

                override fun onFailure(call: Call<MutableList<Place>>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            }
        )
    }
    
    fun getPlace(placeId: Int, onResult: (Place?) -> Unit) {
        retrofit.getPlace(placeId).enqueue(
            object : Callback<Place> {
                override fun onResponse(call: Call<Place>, response: Response<Place>) {
                    Log.d(TAG, "onResponse: ok")
                    val place = response.body()
                    Log.d(TAG, place.toString())
                    onResult(place)
                }

                override fun onFailure(call: Call<Place>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            }
        )
    }

    fun getFreeTime(placeId: Int, date: String, onResult: (MutableList<FreeTime>?) -> Unit) {
        retrofit.getFreeTime(placeId, date).enqueue(
            object : Callback<MutableList<FreeTime>> {
                override fun onResponse(
                    call: Call<MutableList<FreeTime>>,
                    response: Response<MutableList<FreeTime>>
                ) {
                    Log.d(TAG, "onResponse: ok")
                    val freeTime = response.body()
                    Log.d(TAG, freeTime.toString())
                    onResult(freeTime)
                }

                override fun onFailure(call: Call<MutableList<FreeTime>>, t: Throwable) {
                    onResult(null)
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            }
        )
    }
}