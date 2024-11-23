package com.example.nbapp.repository

import com.example.nbapp.data.remote.NBAppApi
import com.example.nbapp.data.remote.responses.PlayerDto
import com.example.nbapp.data.remote.responses.PlayersDataDto
import com.example.nbapp.util.Constants
import com.example.nbapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PlayerListRepository @Inject constructor(
    private val api: NBAppApi
) {
    /**
     * Stores cursor for next pagination.
     */
    private var cursor = 0

    var playerListCache = listOf<PlayerDto>()

    suspend fun getListWithNextPage(): Resource<List<PlayerDto>> {
        val response = try {
            api.getListOfPlayers(cursor, Constants.PER_PAGE)
        } catch (e: Exception) {
            return Resource.Error("Něco se nepovedlo, zkuste to, prosím, znovu." + e.message)
        }
        cursor = response.meta.nextCursor
        playerListCache = playerListCache.plus(response.dataList)
        return Resource.Success(playerListCache)
    }
}