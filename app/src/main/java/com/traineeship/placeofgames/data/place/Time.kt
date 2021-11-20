package com.traineeship.placeofgames.data.place

data class Time(
    val start: String,
    val end: String,
    val isFree: Boolean,
    var isSelected: Boolean = false
) {
    override fun toString(): String {
        return "$start - $end"
    }
}