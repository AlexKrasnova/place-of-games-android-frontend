package com.traineeship.placeofgames.data

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token")
    val value: String
)
