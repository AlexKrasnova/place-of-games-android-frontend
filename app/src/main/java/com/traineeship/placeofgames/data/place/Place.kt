package com.traineeship.placeofgames.data.place

data class Place(
    val id: Int,
    val name: String,
    val address: String,
    val description: String,
    val workingHoursList: List<WorkingHours>
)