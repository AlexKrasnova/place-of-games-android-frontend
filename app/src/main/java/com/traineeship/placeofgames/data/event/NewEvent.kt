package com.traineeship.placeofgames.data.event

import com.traineeship.placeofgames.data.place.Place

data class NewEvent(
    val name: String,
    val time: String,
    val duration: Int,
    val placeId: Int,
    val maxNumberOfParticipants: Int,
    val description: String,
    val category: String,
)