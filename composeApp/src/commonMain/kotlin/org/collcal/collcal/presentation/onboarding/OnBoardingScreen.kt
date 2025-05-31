package org.collcal.collcal.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1

@Composable
fun OnBoardingScreen(navigator: Navigator) {
    val size = when (getPlatformType()) {
        PlatformType.WEB -> 15
        PlatformType.ANDROID -> 10
        PlatformType.IOS -> 10
    }
    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth(0.3f)
        PlatformType.ANDROID -> Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        PlatformType.IOS -> Modifier.fillMaxWidth()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = Strings.appName, fontWeight = FontWeight.W800, fontSize = (size * 4).sp)
        Spacer(Modifier.height(size.dp))
        Text(text = Strings.appDescription, fontWeight = FontWeight.W800, fontSize = (size * 3).sp)

        Spacer(Modifier.height((size * 2).dp))
        Button(
            onClick = { navigator.navigateTo(Screen.SignIn) },
            modifier = modifier,
            shape = RoundedCornerShape(21.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = gray1,
                contentColor = black
            )
        ) {
            Text(
                text = Strings.signIn,
                fontSize = (size * 2).sp,
                modifier = Modifier.padding(vertical = size.dp),
            )
        }

        Spacer(Modifier.height(size.dp))
        Button(
            onClick = { navigator.navigateTo(Screen.SignUp) },
            modifier = modifier,
            shape = RoundedCornerShape(21.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = gray1,
                contentColor = black
            )
        ) {
            Text(
                text = Strings.signUp,
                fontSize = (size * 2).sp,
                modifier = Modifier.padding(vertical = size.dp),
            )
        }
    }
}