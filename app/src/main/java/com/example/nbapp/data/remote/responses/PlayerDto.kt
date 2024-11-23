package com.example.nbapp.data.remote.responses


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlayerDto(
    @SerialName("id")
    val id: Int,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("position")
    val position: String?,
    @SerialName("height")
    val height: String?,
    @SerialName("weight")
    val weight: String?,
    @SerialName("jersey_number")
    val jerseyNumber: String?,
    @SerialName("college")
    val college: String?,
    @SerialName("country")
    val country: String?,
    @SerialName("draft_year")
    val draftYear: Int?,
    @SerialName("draft_round")
    val draftRound: Int?,
    @SerialName("draft_number")
    val draftNumber: Int?,
    @SerialName("team")
    val team: TeamDto
)