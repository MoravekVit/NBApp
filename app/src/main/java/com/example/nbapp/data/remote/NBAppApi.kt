package com.example.nbapp.data.remote

import com.example.nbapp.data.remote.responses.PlayersDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NBAppApi {

    @GET("players")
    suspend fun getListOfPlayers(
        @Query("cursor") cursor: Int,
        @Query("per_page") perPage: Int,
    ): PlayersDataDto
}