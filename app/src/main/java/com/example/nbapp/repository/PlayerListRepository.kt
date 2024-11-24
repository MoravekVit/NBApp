package com.example.nbapp.repository

import com.example.nbapp.data.remote.NBAppApi
import com.example.nbapp.data.remote.responses.Player
import com.example.nbapp.util.Constants
import com.example.nbapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class PlayerListRepository @Inject constructor(
    private val api: NBAppApi
) {
    /**
     * Stores cursor for next pagination.
     */
    private var cursor = 0

    /**
     * Since the same list is used trough whole app and this repository is singleton,
     * we can save players in this variable and access them simply through getter.
     */
    var playerListCache = listOf<Player>()

    /**
     * Downloads the next paginated list of players, adds that list to [playerListCache] and returns
     * whole cache with already added new playes.
     *
     * @return response of api call and if successful, adds new [playerListCache]
     */
    suspend fun getListWithNextPage(): Resource<List<Player>> {
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