package com.example.nbapp.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayersDataDto(
    @SerialName("data")
    val dataList: List<PlayerDto>,
    @SerialName("meta")
    val meta: MetaDto
)