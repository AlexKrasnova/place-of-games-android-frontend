package com.traineeship.placeofgames.data.event

import android.os.Parcel
import android.os.Parcelable

data class Place(
    val id: Int,
    val name: String,
    val address: String,
    val description: String,
    val workingHoursList: List<WorkingHours>
)