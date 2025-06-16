package org.collcal.collcal.presentation.sign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.sign.component.CustomOutlinedTextField
import org.collcal.collcal.presentation.sign.component.CustomPasswordTextField
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun SignInScreen(
    navigator: Navigator,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: SignViewModel = SignViewModel(),
    onClick: (String) -> Unit,
) {
    var idText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth(0.4f)
        PlatformType.ANDROID -> Modifier.fillMaxWidth(0.8f)
        PlatformType.IOS -> Modifier.fillMaxWidth(0.8f)
    }
    val size = when (getPlatformType()) {
        PlatformType.WEB -> 15
        PlatformType.ANDROID -> 10
        PlatformType.IOS -> 10
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Strings.appName,
                fontWeight = FontWeight.W800,
                fontSize = (size * 4).sp,
                color = mainColor
            )
            Text(
                text = Strings.appDescription,
                fontWeight = FontWeight.W800,
                fontSize = (size * 2).sp,
                color = mainColor
            )

            Spacer(Modifier.height((size * 6).dp))
            Column {
                Text(
                    text = Strings.id,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(start = 10.dp, bottom = 2.dp)
                )
                CustomOutlinedTextField(idText, Strings.idEn) { idText = it }
            }

            Spacer(Modifier.height(15.dp))
            Column {
                Text(
                    text = Strings.password,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(start = 10.dp, bottom = 2.dp)
                )
                CustomPasswordTextField(
                    passwordText,
                    Strings.passwordEn,
                    passwordVisible,
                    { passwordText = it },
                    { passwordVisible = !passwordVisible }
                )
            }

            Spacer(Modifier.height((size * 6).dp))
            Button(
                onClick = { viewModel.signIn(idText, passwordText) { onClick(it) } },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(21.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = mainColor,
                    contentColor = mainColor
                )
            ) {
                Text(text = Strings.signIn, color = white)
            }

            Spacer(Modifier.height(15.dp))
            Text(
                text = Strings.signUp,
                modifier = Modifier.clickable(
                    onClick = { navigator.navigateTo(Screen.SignUp) },
                    interactionSource = null,
                    indication = null
                )
            )
        }
    }
}