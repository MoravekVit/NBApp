package com.example.nbapp.screens.playerdetail

import androidx.lifecycle.ViewModel
import com.example.nbapp.data.remote.responses.Player
import com.example.nbapp.repository.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val repository: PlayerListRepository
) : ViewModel() {

    private val _playerDetailState = MutableStateFlow(PlayerDetailState(null))

    val playerDetailState: StateFlow<PlayerDetailState> = _playerDetailState

    /**
     * Is called onCreate so we know the screen was created and we want to get data from repository.
     *
     * @param playerId for which player are we getting data.
     */
    fun onScreenOpened(playerId: Int) {
        _playerDetailState.value = PlayerDetailState(repository.playerListCache.first { it.id == playerId })
    }
}

/**
 * Current state of Player Detail screen
 *
 * @property player data about selected player
 */
data class PlayerDetailState(
    val player: Player?
)