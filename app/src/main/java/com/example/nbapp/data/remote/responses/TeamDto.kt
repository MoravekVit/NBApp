package com.example.nbapp.data.remote.responses


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    @SerialName("id")
    val id: Int,
    @SerialName("conference")
    val conference: String,
    @SerialName("division")
    val division: String,
    @SerialName("city")
    val city: String,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("abbreviation")
    val abbreviation: String
)