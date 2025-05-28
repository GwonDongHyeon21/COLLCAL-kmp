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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.onboarding.OnBoardingScreen
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.user.UserScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollCalApp() {
    val navigator = remember { AndroidNavigator() }
    val currentScreen by navigator.currentScreen
    val currentScreenSize by navigator.currentScreenSize

    val isTopBar = currentScreen == Screen.OnBoarding.route
    val isBottomBar = currentScreen !in listOf(
        Screen.OnBoarding.route,
        Screen.SignIn.route,
        Screen.SignUp.route
    )

    if (currentScreenSize > 1) BackHandler { navigator.goBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (!isTopBar) Text(
                        text = Strings.appName,
                        fontWeight = FontWeight.W800
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isTopBar) Color.Transparent else gray1
                ),
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = if (isBottomBar) gray1 else Color.Transparent) {
                if (isBottomBar) Row { Text(text = "test") }
            }
        }
    ) { innerPadding ->
        when (currentScreen) {
            Screen.OnBoarding.route -> OnBoardingScreen(navigator)
            Screen.SignIn.route -> SignInScreen(navigator, innerPadding)
            Screen.SignUp.route -> SignUpScreen(navigator, innerPadding)
            Screen.College.route -> CollegeScreen(navigator, innerPadding)
            Screen.User.route -> UserScreen(navigator, innerPadding)
        }
    }
}