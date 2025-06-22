package org.collcal.collcal

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import org.collcal.collcal.component.BottomBar
import org.collcal.collcal.component.TopBar
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.tasks.TasksScreen
import org.collcal.collcal.presentation.user.UserScreen

@Composable
fun CollCalApp() {
    val context = LocalContext.current
    val navigator = remember { AndroidNavigator() }
    val viewModel = remember { CollegeViewModel() }
    val signViewModel = remember { SignViewModel() }
    val currentScreen by navigator.currentScreen
    val currentScreenSize by navigator.currentScreenSize

    val isTopBar = currentScreen !in listOf(
        Screen.SignIn.route,
        Screen.SignUp.route
    )
    val isBottomBar = currentScreen !in listOf(
        Screen.SignIn.route,
        Screen.SignUp.route
    )
    val items = listOf(
        Triple("홈", Icons.Default.Home, Screen.College),
        Triple("할 일", Icons.AutoMirrored.Filled.List, Screen.Tasks),
        Triple("마이페이지", Icons.Default.AccountCircle, Screen.User)
    )
    var tabScreen by remember { mutableStateOf(items.first().third.route) }

    LaunchedEffect(Unit) {
        viewModel.getUser { navigator.resetTo(Screen.SignIn) }
    }
    LaunchedEffect(currentScreen) {
        if (items.any { it.third.route == currentScreen })
            tabScreen = currentScreen
    }

    if (currentScreenSize > 1) BackHandler { navigator.goBack() }

    Scaffold(
        topBar = { if (isTopBar) TopBar() },
        bottomBar = { if (isBottomBar) BottomBar(items, navigator, tabScreen) }
    ) { innerPadding ->
        when (currentScreen) {
            Screen.SignIn.route -> SignInScreen(navigator, signViewModel, innerPadding) {
                navigator.resetTo(Screen.College)
                saveUserToken(context, it)
            }

            Screen.SignUp.route -> SignUpScreen(navigator, signViewModel, innerPadding)
            Screen.College.route -> CollegeScreen(navigator, viewModel, innerPadding)
            Screen.Tasks.route -> TasksScreen(navigator, viewModel, innerPadding) {}
            Screen.User.route -> UserScreen(navigator, viewModel, innerPadding)
        }
    }
}

fun saveUserToken(context: Context, token: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("CollCal", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("CollCalToken", token)
    editor.apply()
}