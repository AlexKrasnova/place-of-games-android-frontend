package com.traineeship.placeofgames.data.event

import com.traineeship.placeofgames.data.place.Place

data class Event(
    val id: Int,
    val name: String,
    val time: String,
    val duration: Int,
    val place: Place,
    val maxNumberOfParticipants: Int,
    val numberOfParticipants: Int,
    val description: String,
    val category: Category,
    val isCurrentUserOwner: Boolean,
    val isCurrentUserEnrolled: Boolean,
    val participants: List<Participant>?
)