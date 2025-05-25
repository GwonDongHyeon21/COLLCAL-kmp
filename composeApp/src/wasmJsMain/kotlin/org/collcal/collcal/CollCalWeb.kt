package org.collcal.collcal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.browser.window
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.navigation.WebNavigator
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.onboarding.OnBoardingScreen
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.user.UserScreen

@Composable
fun CollCalWeb() {
    val navigator = remember { WebNavigator() }
    val currentScreen by navigator.currentScreen

    DisposableEffect(Unit) {
        val listener: (org.w3c.dom.PopStateEvent) -> Unit = {
            navigator.onPopState()
        }
        window.onpopstate = listener
        onDispose {
            window.onpopstate = null
        }
    }

    when (currentScreen) {
        Screen.OnBoarding.route -> OnBoardingScreen(navigator)
        Screen.SignIn.route -> SignInScreen(navigator)
        Screen.SignUp.route -> SignUpScreen(navigator)
        Screen.College.route -> CollegeScreen(navigator)
        Screen.User.route -> UserScreen(navigator)
    }
}