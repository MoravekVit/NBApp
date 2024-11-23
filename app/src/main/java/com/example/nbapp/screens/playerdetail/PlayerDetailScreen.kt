package com.example.nbapp.screens.playerdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.nbapp.R
import com.example.nbapp.TeamDetail
import com.example.nbapp.data.remote.responses.PlayerDto
import com.example.nbapp.data.remote.responses.TeamDto
import com.example.nbapp.ui.theme.NBABlue
import com.example.nbapp.ui.theme.NBARed
import com.example.nbapp.ui.theme.NBAppTheme

@Composable
fun PlayerDetailScreen(
    playerId: Int,
    navController: NavController,
    playerDetailViewModel: PlayerDetailViewModel = hiltViewModel()
) {
    val currentPlayer = remember { playerDetailViewModel.getPlayer(playerId) }

    PlayerDetailComposable(
        currentPlayer,
        {
            navController.navigate(TeamDetail(playerId))
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailComposable(
    playerDto: PlayerDto,
    onTeamDetailClicked: () -> Unit
) {
    Surface(color = NBABlue, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeContent)
        ) {
            Text(
                text = playerDto.jerseyNumber ?: "",
                fontFamily = FontFamily(Font(R.font.atlanta_college)),
                fontSize = 80.sp,
                color = NBARed,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )
            ElevatedCard(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "${playerDto.firstName} ${playerDto.lastName}",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Tým: ${playerDto.team.fullName}", color = Color.Black, fontSize = 20.sp)
                    Text(
                        "Pozice: ${playerDto.position ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Výška: ${playerDto.height ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Váha: ${playerDto.weight ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Univerzita: ${playerDto.college ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Stát: ${playerDto.country ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Draftován v roce: ${playerDto.draftYear ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Kolo draftu: ${playerDto.draftRound ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Text(
                        "Číslo v draftu: ${playerDto.draftNumber ?: "Neznámé"}",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Button(
                        onClick = onTeamDetailClicked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    ) {
                        Text("Detail Týmu")
                    }
                }
            }
            GlideImage(
                model = "https://cdn.nba.com/manage/2020/10/NBA20Secondary20Logo-784x462.jpg",
                contentDescription = "NBA Logo",
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )
        }
    }
}

@Composable
@Preview
fun PlayerDetailPreview() {
    NBAppTheme {
        PlayerDetailComposable(
            PlayerDto(
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
                TeamDto(
                    1,
                    "Eastern",
                    "Atlantic",
                    "Chicago",
                    "Bulls",
                    "Chicago Bulls",
                    "CHB"
                )
            ), {})
    }
}