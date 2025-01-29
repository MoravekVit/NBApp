package com.example.nbapp.screens.teamdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.nbapp.TeamDetail
import com.example.nbapp.data.remote.responses.Team
import com.example.nbapp.repository.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlayerListRepository
) : ViewModel() {

    private val _teamDetailState = MutableStateFlow(TeamDetailState(null))

    val teamDetailState: StateFlow<TeamDetailState> = _teamDetailState

    /**
     * Getting navigation argument here for selected player.
     */
    init {
        val args = savedStateHandle.toRoute<TeamDetail>()
        val playerId = args.playerId
        _teamDetailState.value =
            TeamDetailState(repository.playerListCache.first { it.id == playerId }.team)
    }
}

/**
 * Current state of Team detail screen.
 *
 * @property team data about selected Team.
 */
data class TeamDetailState(
    val team: Team?
)

