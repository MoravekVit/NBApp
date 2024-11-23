package com.example.nbapp.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto(
    @SerialName("prev_cursor")
    val prevCursor: Int? = null,
    @SerialName("next_cursor")
    val nextCursor: Int,
    @SerialName("per_page")
    val perPage: Int
)