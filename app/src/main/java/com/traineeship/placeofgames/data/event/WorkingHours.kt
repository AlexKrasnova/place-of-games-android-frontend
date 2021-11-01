package com.traineeship.placeofgames.data.event

import android.os.Parcel
import android.os.Parcelable

data class WorkingHours(
    val dayOfWeek: String,
    val date: String?,
    val startTime: String,
    val endTime: String
) {
    override fun toString(): String {
        return "$dayOfWeek $startTime-$endTime"
    }
}
