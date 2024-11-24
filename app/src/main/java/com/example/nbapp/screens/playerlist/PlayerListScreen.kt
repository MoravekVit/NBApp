package com.example.nbapp.screens.playerlist

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.nbapp.PlayerDetail
import com.example.nbapp.R
import com.example.nbapp.data.remote.responses.Player
import com.example.nbapp.data.remote.responses.Team
import com.example.nbapp.ui.theme.NBABlue
import com.example.nbapp.ui.theme.NBARed
import com.example.nbapp.ui.theme.NBAppTheme

@Composable
fun PlayerListScreen(
    navController: NavController, playerListViewModel: PlayerListViewModel = hiltViewModel()
) {
    val playerListState by playerListViewModel.playerListState.collectAsState()

    PlayerListComposable(
        isLoading = playerListState.isLoading,
        listOfPlayers = playerListState.listOfPlayers,
        errorMessage = playerListState.loadError,
        onPaginationReached = {
            playerListViewModel.loadPlayersPaginated()
        },
        onPlayerClicked = { playerId ->
            navController.navigate(PlayerDetail(playerId))
        })
}

@Composable
fun PlayerListComposable(
    isLoading: Boolean,
    listOfPlayers: List<Player>,
    errorMessage: String,
    onPaginationReached: () -> Unit,
    onPlayerClicked: (Int) -> Unit
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_anim))

    Surface(color = NBABlue, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeContent)
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(listOfPlayers.size) { index ->
                    if (index >= listOfPlayers.size - 1) {
                        onPaginationReached()
                    }
                    PlayerItemView(
                        listOfPlayers[index], { playerId ->
                            onPlayerClicked(playerId)
                        },
                        Modifier
                            .padding(8.dp)
                            .fillParentMaxWidth()
                    )
                }
                if (isLoading) {
                    item {
                        LottieAnimation(
                            composition,
                            iterations = LottieConstants.IterateForever,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth(0.4f)
                        )
                    }
                }
            }
            if (errorMessage.isNotEmpty()) {
                Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
fun PlayerItemView(
    currentPlayer: Player,
    onPlayerClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = {
            onPlayerClicked(currentPlayer.id)
        },
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        modifier = modifier
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    currentPlayer.lastName ?: "",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(currentPlayer.firstName ?: "", color = Color.Black)
                Text(
                    currentPlayer.team.fullName, color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(currentPlayer.position ?: "", color = Color.Black, fontSize = 36.sp)
            Text(
                currentPlayer.jerseyNumber ?: "",
                fontFamily = FontFamily(Font(R.font.atlanta_college)),
                fontSize = 80.sp,
                color = NBARed,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

}

@Composable
@Preview
fun PlayerListPreview() {
    NBAppTheme {
        PlayerListComposable(
            isLoading = true, listOf(
                Player(
                    1,
                    "Franta",
                    "Pepa",
                    "F",
                    "188",
                    "95",
                    "48",
                    "Yale",
                    "Czechia",
                    2008,
                    12,
                    1,
                    Team(
                        1,
                        "Eastern",
                        "Atlantic",
                        "Chicago",
                        "Bulls",
                        "Chicago Bulls",
                        "CHB"
                    )
                ),
                Player(
                    1,
                    "Franta",
                    "Pepa",
                    "F",
                    "188",
                    "95",
                    "48",
                    "Yale",
                    "Czechia",
                    2008,
                    12,
                    1,
                    Team(
                        1,
                        "Eastern",
                        "Atlantic",
                        "Chicago",
                        "Bulls",
                        "Chicago Bulls",
                        "CHB"
                    )
                ),
                Player(
                    1,
                    "Franta",
                    "Pepa",
                    "F",
                    "188",
                    "95",
                    "48",
                    "Yale",
                    "Czechia",
                    2008,
                    12,
                    1,
                    Team(
                        1,
                        "Eastern",
                        "Atlantic",
                        "Chicago",
                        "Bulls",
                        "Chicago Bulls",
                        "CHB"
                    )
                ),
            ), "", {}, {}
        )
    }
}