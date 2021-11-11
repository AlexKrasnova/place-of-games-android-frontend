package com.traineeship.placeofgames.data.user

data class User(
    val login: String,
    val password: String,
    val name: String,
    val id: Int? = null
)
