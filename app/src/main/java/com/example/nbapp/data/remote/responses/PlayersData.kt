package com.example.nbapp.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayersData(
    @SerialName("data")
    val dataList: List<Player>,
    @SerialName("meta")
    val meta: Meta
)