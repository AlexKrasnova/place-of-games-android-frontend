package com.example.placeofgames.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Event(
    val id: Int,
    val name: String,
    val time: String,
    val duration: Int,
    val place: Place,

    @SerializedName("maxNumberOfParticipants")
    val maxPeopleNum: Int,

    @SerializedName("numberOfParticipants")
    val currentPeopleNum: Int
)
