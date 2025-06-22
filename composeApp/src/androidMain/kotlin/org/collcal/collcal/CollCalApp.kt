package org.collcal.collcal

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import org.collcal.collcal.MainActivity.Companion.token
import org.collcal.collcal.component.BottomBar
import org.collcal.collcal.component.Token.deleteUserToken
import org.collcal.collcal.component.Token.saveUserToken
import org.collcal.collcal.component.TopBar
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.taskdetail.TaskDetailScreen
import org.collcal.collcal.presentation.tasks.TasksScreen
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.user.UserScreen

@Composable
fun CollCalApp() {
    val context = LocalContext.current
    val navigator = remember { AndroidNavigator() }
    val viewModel = remember { CollegeViewModel() }
    val signViewModel = remember { SignViewModel() }
    val currentScreen by navigator.currentScreen
    val currentScreenSize by navigator.currentScreenSize
    val isLoading by viewModel.isLoading.collectAsState()

    val isTopBar = currentScreen !in listOf(
        Screen.SignIn.route,
        Screen.SignUp.route
    )
    val isBottomBar = currentScreen !in listOf(
        Screen.SignIn.route,
        Screen.SignUp.route
    )
    val items = listOf(
        Triple(Strings.home, Icons.Default.Home, Screen.College),
        Triple(Strings.todos, Icons.AutoMirrored.Filled.List, Screen.Tasks),
        Triple(Strings.todos, Icons.Default.AccountCircle, Screen.User)
    )
    var tabScreen by remember { mutableStateOf(items.first().third.route) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    LaunchedEffect(currentScreen) {
        if (items.any { it.third.route == currentScreen })
            tabScreen = currentScreen
    }

    if (currentScreenSize > 1) BackHandler { navigator.goBack() }

    Scaffold(
        topBar = { if (isTopBar && !isLoading) TopBar() },
        bottomBar = { if (isBottomBar && !isLoading) BottomBar(items, navigator, tabScreen) }
    ) { innerPadding ->
        when (currentScreen) {
            Screen.SignIn.route -> SignInScreen(navigator, signViewModel, innerPadding) {
                saveUserToken(context, it)
                token = it
                navigator.resetTo(Screen.College)
            }

            Screen.SignUp.route -> SignUpScreen(navigator, signViewModel, innerPadding)
            Screen.College.route -> CollegeScreen(navigator, viewModel, {
                selectedTask = it
                navigator.navigateTo(Screen.TaskDetail)
            }, {}, innerPadding)

            Screen.Tasks.route -> TasksScreen(navigator, viewModel, {}, {
                selectedTask = it
                navigator.navigateTo(Screen.TaskDetail)
            }, innerPadding)

            Screen.TaskDetail.route -> TaskDetailScreen(
                selectedTask,
                innerPadding
            ) { navigator.goBack() }

            Screen.User.route -> UserScreen(navigator, viewModel, innerPadding) {
                deleteUserToken(context)
                navigator.resetTo(Screen.SignIn)
            }
        }
    }
}