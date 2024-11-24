package com.example.nbapp.screens.teamdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.nbapp.data.remote.responses.Team
import com.example.nbapp.ui.theme.NBABlue
import com.example.nbapp.ui.theme.NBAppTheme

@Composable
fun TeamDetailScreen(
    playerId: Int,
    navController: NavController,
    teamDetailViewModel: TeamDetailViewModel = hiltViewModel()
) {
    val teamDetailState by teamDetailViewModel.teamDetailState.collectAsState()

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.CREATED) {
            teamDetailViewModel.onScreenOpened(playerId)
        }
    }

    TeamDetailComposable(teamDetailState.team) {
        navController.popBackStack()
    }
}

@Composable
fun TeamDetailComposable(team: Team?, onGoBackClicked: () -> Unit) {
    Surface(color = NBABlue, modifier = Modifier.fillMaxSize()) {
        // This could be handled better (error message etc.)
        team?.let {
            Box {
                ElevatedCard(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center)
                ) {
                    Column(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            team.fullName,
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Konference: ${team.conference}",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                        Text("Divize: ${team.division}", color = Color.Black, fontSize = 20.sp)
                        Text("Město: ${team.city}", color = Color.Black, fontSize = 20.sp)
                        Text(
                            "Zkratka: ${team.abbreviation}",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                        Button(
                            onClick = onGoBackClicked,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                        ) {
                            Text("Zpátky na detail hráče")
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TeamDetailPreview() {
    NBAppTheme {
        TeamDetailComposable(
            Team(
                1,
                "Eastern",
                "Atlantic",
                "Chicago",
                "Bulls",
                "Chicago Bulls",
                "CHB"
            ), {}
        )
    }
}