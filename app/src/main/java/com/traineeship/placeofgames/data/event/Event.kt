package com.traineeship.placeofgames.data.event

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Event(
    val id: Int,
    val name: String,
    val time: String,
    val duration: Int,
    val place: Place,

    @SerializedName("maxNumberOfParticipants")
    val maxPeopleNum: Int,

    @SerializedName("numberOfParticipants")
    val currentPeopleNum: Int,

    val description: String,
    val isCurrentUserEnrolled: Boolean
)