package com.example.nbapp.screens.playerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbapp.data.remote.responses.PlayerDto
import com.example.nbapp.repository.PlayerListRepository
import com.example.nbapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val repository: PlayerListRepository
) : ViewModel() {

    private val _playerListSate = MutableStateFlow(PlayerListState())

    val playerListState: StateFlow<PlayerListState> = _playerListSate

    init {
        loadPlayersPaginated()
    }

    /**
     * Loads list of players from api.
     * Since Meta(data) contains next cursor, we also want to store it locally, so we know which cursor is next.
     */
    fun loadPlayersPaginated() {
        viewModelScope.launch(Dispatchers.IO) {
            _playerListSate.value = _playerListSate.value.copy(isLoading = true)
            val result = repository.getListWithNextPage()
            when (result) {
                is Resource.Success -> {
                    // For some reason recieved data are empty, so we assume something went wrong and display error
                    if (result.data == null) {
                        showError()
                    } else {
                        _playerListSate.value = _playerListSate.value.copy(
                            isLoading = false,
                            listOfPlayers = result.data,
                            loadError = ""
                        )
                    }
                }

                is Resource.Error -> {
                    showError(result.message)
                }
            }
        }
    }

    /**
     * Function to show error with some default error message.
     * @param errorMessage contains the message which will be shown
     */
    private fun showError(errorMessage: String? = null) {
        _playerListSate.value = _playerListSate.value.copy(
            isLoading = false,
            loadError = errorMessage ?: "Něco se nepovedlo, zkuste to, prosím, znovu."
        )
    }

}

/**
 * Current state of Player list screen.
 *
 * @param isLoading whether loader should be displayed.
 * @param listOfPlayers contains all currently displayed players
 * @param loadError when API call fails, this is what is displayed to user
 */
data class PlayerListState(
    val isLoading: Boolean = true,
    val listOfPlayers: List<PlayerDto> = listOf(),
    val loadError: String = ""
)