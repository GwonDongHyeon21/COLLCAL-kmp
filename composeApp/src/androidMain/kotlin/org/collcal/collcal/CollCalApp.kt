package org.collcal.collcal

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.onboarding.OnBoardingScreen
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.tasks.TasksScreen
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.transparent
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
    val items = listOf(
        Triple("홈", Icons.Default.Home, Screen.College),
        Triple("할 일", Icons.AutoMirrored.Filled.List, Screen.Tasks),
        Triple("마이페이지", Icons.Default.AccountCircle, Screen.User)
    )
    var tabScreen by remember { mutableStateOf(items.first().third.route) }

    LaunchedEffect(currentScreen) {
        if (items.any { it.third.route == currentScreen })
            tabScreen = currentScreen
    }

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
                    containerColor = if (isTopBar) transparent else gray1
                ),
            )
        },
        bottomBar = {
            NavigationBar(containerColor = if (isBottomBar) gray1 else transparent) {
                if (isBottomBar)
                    items.forEach {
                        NavigationBarItem(
                            icon = { Icon(imageVector = it.second, contentDescription = it.first) },
                            label = { Text(text = it.first) },
                            selected = tabScreen == it.third.route,
                            onClick = {
                                navigator.resetAndNavigateTo(
                                    items.first().third,
                                    it.third
                                )
                            }
                        )
                    }
            }
        }
    ) { innerPadding ->
        when (currentScreen) {
            Screen.OnBoarding.route -> OnBoardingScreen(navigator)
            Screen.SignIn.route -> SignInScreen(innerPadding) { navigator.resetTo(Screen.College) }
            Screen.SignUp.route -> SignUpScreen(navigator, innerPadding)
            Screen.College.route -> CollegeScreen(navigator, innerPadding)
            Screen.Tasks.route -> TasksScreen(navigator, innerPadding)
            Screen.User.route -> UserScreen(navigator, innerPadding)
        }
    }
}