package org.collcal.collcal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.onboarding.OnBoardingScreen
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.user.UserScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollCalApp() {
    val navigator = remember { AndroidNavigator() }
    val currentScreen by navigator.currentScreen
    val currentScreenSize by navigator.currentScreenSize

    if (currentScreenSize > 1) BackHandler { navigator.goBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "test") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = gray1)
            )
        },
        bottomBar = { BottomAppBar { Row { Text(text = "test") } } }
    ) { paddingValues ->
        when (currentScreen) {
            Screen.OnBoarding.route -> OnBoardingScreen(navigator)
            Screen.SignIn.route -> SignInScreen(navigator)
            Screen.SignUp.route -> SignUpScreen(navigator)
            Screen.College.route -> CollegeScreen(navigator, paddingValues)
            Screen.User.route -> UserScreen(navigator, paddingValues)
        }
    }
}