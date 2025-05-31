package org.collcal.collcal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import org.collcal.collcal.component.getCurrentDateFormatted
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.navigation.WebNavigator
import org.collcal.collcal.presentation.college.CollegeScreen
import org.collcal.collcal.presentation.onboarding.OnBoardingScreen
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray1
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

    Column {
        if (currentScreen != Screen.OnBoarding.route) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.appName,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = getCurrentDateFormatted(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider(color = gray1)
        }

        when (currentScreen) {
            Screen.OnBoarding.route -> OnBoardingScreen(navigator)
            Screen.SignIn.route -> SignInScreen { navigator.replaceTo(Screen.College) }
            Screen.SignUp.route -> SignUpScreen(navigator)
            Screen.College.route -> CollegeScreen(navigator)
            Screen.User.route -> UserScreen(navigator)
        }
    }
}