package org.collcal.collcal.presentation.sign

import androidx.compose.foundation.background
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
import org.collcal.collcal.PlatformType
import org.collcal.collcal.getPlatformType
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.sign.component.CustomOutlinedTextField
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray2

@Composable
fun SignInScreen(
    navigator: Navigator,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    var idText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth(0.4f)
        PlatformType.ANDROID -> Modifier.fillMaxWidth(0.8f)
        PlatformType.IOS -> Modifier.fillMaxWidth(0.8f)
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        if (getPlatformType() == PlatformType.WEB) {
            Text(
                text = Strings.signIn,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.align(Alignment.TopStart).padding(start = 60.dp, top = 30.dp)
            )
        }

        Column(
            modifier = modifier
                .background(gray2, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Text(text = Strings.id, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(idText) { idText = it }

            Spacer(Modifier.height(10.dp))
            Text(text = Strings.password, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(passwordText) { passwordText = it }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { navigator.navigateTo(Screen.College) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(21.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = gray1,
                    contentColor = black
                )
            ) {
                Text(text = Strings.signIn)
            }
        }
    }
}