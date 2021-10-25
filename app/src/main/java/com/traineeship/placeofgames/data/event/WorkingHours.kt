package com.traineeship.placeofgames.data.event

import android.os.Parcel
import android.os.Parcelable

data class WorkingHours(
    val dayOfWeek: String?,
    val date: String?,
    val startTime: String?,
    val endTime: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dayOfWeek)
        parcel.writeString(date)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorkingHours> {
        override fun createFromParcel(parcel: Parcel): WorkingHours {
            return WorkingHours(parcel)
        }

        override fun newArray(size: Int): Array<WorkingHours?> {
            return arrayOfNulls(size)
        }
    }
}
