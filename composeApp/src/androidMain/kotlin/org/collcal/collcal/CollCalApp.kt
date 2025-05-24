package org.collcal.collcal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.home.HomeScreen
import org.collcal.collcal.presentation.user.UserScreen

@Composable
fun CollCalApp() {
    val navigator = remember { AndroidNavigator() }
    val currentScreen by navigator.currentScreen
    val currentScreenSize by navigator.currentScreenSize

    if (currentScreenSize > 1)
        BackHandler { navigator.goBack() }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row { Text(text = "text") }
            }
        }
    ) { paddingValues ->
        when (currentScreen) {
            Screen.Home.route -> HomeScreen(navigator, paddingValues)
            Screen.College.route -> CollegeScreen(navigator, paddingValues)
            Screen.User.route -> UserScreen(navigator, paddingValues)
        }
    }
}