package org.collcal.collcal

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.collcal.collcal.navigation.NavigatorGraph
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.home.HomeScreen
import org.collcal.collcal.presentation.user.UserScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollCalApp() {
    val navigator = remember { NavigatorGraph() }
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
    ) {
        when (currentScreen) {
            Screen.Home.route -> HomeScreen(navigator)
            Screen.College.route -> CollegeScreen(navigator)
            Screen.User.route -> UserScreen(navigator)
        }
    }
}