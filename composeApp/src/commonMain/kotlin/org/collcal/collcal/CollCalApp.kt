package org.collcal.collcal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.collcal.collcal.navigation.NavigationController
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.home.HomeScreen
import org.collcal.collcal.presentation.user.UserScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun CollCalApp() {
    val navController = remember { NavigationController() }

    MaterialTheme {
        when (navController.currentScreen) {
            is Screen.Home -> HomeScreen(onNavigateToUser = { navController.navigate(Screen.User) })
            is Screen.User -> UserScreen(onBack = { navController.goBack() })
        }
    }
}