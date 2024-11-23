package com.example.nbapp.screens.teamdetail

import androidx.lifecycle.ViewModel
import com.example.nbapp.data.remote.responses.TeamDto
import com.example.nbapp.repository.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val repository: PlayerListRepository
) : ViewModel() {

    fun getPlayersTeam(playerId: Int): TeamDto {
        return repository.playerListCache.first { it.id == playerId }.team
    }
}