package org.collcal.collcal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
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
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.sign.SignInScreen
import org.collcal.collcal.presentation.sign.SignUpScreen
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.mainColor

@Composable
fun CollCalWeb() {
    val navigator = remember { WebNavigator() }
    val viewModel = remember { CollegeViewModel() }
    val signViewModel = remember { SignViewModel() }
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
        if (currentScreen != Screen.SignIn.route) {
            Surface(
                shadowElevation = 10.dp,
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                shape = RoundedCornerShape(bottomStart = 19.dp, bottomEnd = 19.dp),
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = Strings.appName,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.W800,
                        color = mainColor
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = getCurrentDateFormatted(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W800,
                        color = mainColor
                    )
                }
            }
        }

        when (currentScreen) {
            Screen.SignIn.route -> SignInScreen(navigator, signViewModel) {
                token = it
                navigator.replaceTo(Screen.College)
            }

            Screen.SignUp.route -> SignUpScreen(navigator, signViewModel)
            Screen.College.route -> CollegeScreen(navigator, viewModel, {}, { navigator.refresh() })
        }
    }
}