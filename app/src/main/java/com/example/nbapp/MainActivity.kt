package com.example.nbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.nbapp.screens.playerdetail.PlayerDetailScreen
import com.example.nbapp.screens.playerlist.PlayerListScreen
import com.example.nbapp.screens.teamdetail.TeamDetailScreen
import com.example.nbapp.ui.theme.NBAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NBAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = PlayerList
                ) {
                    composable<PlayerList> {
                        PlayerListScreen(navController)
                    }
                    composable<PlayerDetail> { navBackStackEntry ->
                        val playerDetail: PlayerDetail = navBackStackEntry.toRoute()
                        PlayerDetailScreen(playerDetail.playerId, navController)
                    }
                    composable<TeamDetail> { navBackStackEntry ->
                        val teamDetail: TeamDetail = navBackStackEntry.toRoute()
                        TeamDetailScreen(teamDetail.playerId, navController)
                    }
                }
            }
        }
    }
}

@Serializable
object PlayerList

@Serializable
data class PlayerDetail(val playerId: Int)

@Serializable
data class TeamDetail(val playerId: Int)
