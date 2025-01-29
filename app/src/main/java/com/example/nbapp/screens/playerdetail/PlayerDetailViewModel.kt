package com.example.nbapp.screens.playerdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.nbapp.PlayerDetail
import com.example.nbapp.data.remote.responses.Player
import com.example.nbapp.repository.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlayerListRepository
) : ViewModel() {

    private val _playerDetailState = MutableStateFlow(PlayerDetailState(null))

    val playerDetailState: StateFlow<PlayerDetailState> = _playerDetailState

    /**
     * Getting navigation argument here for selected player.
     */
    init {
        val args = savedStateHandle.toRoute<PlayerDetail>()
        val playerId = args.playerId
        _playerDetailState.value =
            PlayerDetailState(repository.playerListCache.first { it.id == playerId })
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