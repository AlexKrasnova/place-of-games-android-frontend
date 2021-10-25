package com.traineeship.placeofgames.data.event

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Event(
    val id: Int,
    val name: String?,
    val time: String?,
    val duration: Int,
    val place: Place?,

    @SerializedName("maxNumberOfParticipants")
    val maxPeopleNum: Int,

    @SerializedName("numberOfParticipants")
    val currentPeopleNum: Int,

    val description: String?,
    val isCurrentUserEnrolled: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(Place::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(time)
        parcel.writeInt(duration)
        parcel.writeParcelable(place, flags)
        parcel.writeInt(maxPeopleNum)
        parcel.writeInt(currentPeopleNum)
        parcel.writeString(description)
        parcel.writeByte(if (isCurrentUserEnrolled) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }

}
