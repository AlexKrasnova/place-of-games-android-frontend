package com.traineeship.placeofgames.data.user

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token")
    val value: String
)
