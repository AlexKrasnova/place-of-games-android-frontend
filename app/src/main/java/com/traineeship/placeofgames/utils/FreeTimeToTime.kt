package com.traineeship.placeofgames.utils

import com.traineeship.placeofgames.data.place.FreeTime
import com.traineeship.placeofgames.data.place.Time
import java.text.SimpleDateFormat
import java.util.*

const val ONE_MINUTE_IN_MILLIS: Long = 60000 //millisecs

fun freeTimeToTime(freeTime: List<FreeTime>): List<Time> {
    val time = mutableListOf<Time>()
    val sdf = SimpleDateFormat("HH:mm")
    freeTime.forEach {

        var startCurrent = sdf.parse(it.startTime.split("T")[1]).time
        var endCurrent = sdf.parse(it.startTime.split("T")[1]).time + 30 * ONE_MINUTE_IN_MILLIS
        val end = sdf.parse(it.endTime.split("T")[1]).time

        while (startCurrent < end){
            time.add(Time(sdf.format(Date(startCurrent)), sdf.format(Date(endCurrent)), true))
            startCurrent += 30 * ONE_MINUTE_IN_MILLIS
            endCurrent += 30 * ONE_MINUTE_IN_MILLIS
        }

        time.add(Time("", "", false))
    }
    time.removeLast()
    return time
}

fun dateToQueryDate(date: String): String {
   return date.split("-").reversed().joinToString(separator = "-")
}