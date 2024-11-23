package com.example.nbapp.screens.playerdetail

import androidx.lifecycle.ViewModel
import com.example.nbapp.data.remote.responses.PlayerDto
import com.example.nbapp.repository.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val repository: PlayerListRepository
) : ViewModel() {

    fun getPlayer(playerId: Int): PlayerDto {
        return repository.playerListCache.first { it.id == playerId }
    }
}