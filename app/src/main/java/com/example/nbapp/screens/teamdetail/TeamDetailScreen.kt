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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nbapp.data.remote.responses.TeamDto
import com.example.nbapp.ui.theme.NBABlue
import com.example.nbapp.ui.theme.NBAppTheme

@Composable
fun TeamDetailScreen(
    playerId: Int,
    navController: NavController,
    teamDetailViewModel: TeamDetailViewModel = hiltViewModel()
) {
    TeamDetailComposable(teamDetailViewModel.getPlayersTeam(playerId), {
        navController.popBackStack()
    })
}

@Composable
fun TeamDetailComposable(teamDto: TeamDto, onGoBackClicked: () -> Unit) {
    Surface(color = NBABlue, modifier = Modifier.fillMaxSize()) {
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
                        teamDto.fullName,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Konference: ${teamDto.conference}", color = Color.Black, fontSize = 20.sp)
                    Text("Divize: ${teamDto.division}", color = Color.Black, fontSize = 20.sp)
                    Text("Město: ${teamDto.city}", color = Color.Black, fontSize = 20.sp)
                    Text("Zkratka: ${teamDto.abbreviation}", color = Color.Black, fontSize = 20.sp)
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

@Composable
@Preview
fun TeamDetailPreview() {
    NBAppTheme {
        TeamDetailComposable(
            TeamDto(
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