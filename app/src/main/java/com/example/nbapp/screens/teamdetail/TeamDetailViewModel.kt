package com.example.nbapp.screens.teamdetail

import androidx.lifecycle.ViewModel
import com.example.nbapp.data.remote.responses.Team
import com.example.nbapp.repository.PlayerListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val repository: PlayerListRepository
) : ViewModel() {

    private val _teamDetailState = MutableStateFlow(TeamDetailState(null))

    val teamDetailState: StateFlow<TeamDetailState> = _teamDetailState

    /**
     * Is called onCreate so we know the screen was created and we want to get data from repository.
     *
     * @param playerId for which player's team are we getting data.
     */
    fun onScreenOpened(playerId: Int) {
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

