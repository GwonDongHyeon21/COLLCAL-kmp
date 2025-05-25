package org.collcal.collcal.presentation.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.collcal.collcal.PlatformType
import org.collcal.collcal.getPlatformType
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray2

@Composable
fun SignInScreen(navigator: Navigator) {
    var idText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth(0.4f)
        PlatformType.ANDROID -> Modifier.fillMaxWidth(0.8f)
        PlatformType.IOS -> Modifier.fillMaxWidth(0.8f)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .background(gray2, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Text(text = Strings.id, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = idText,
                onValueChange = { idText = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp),
                shape = RoundedCornerShape(21.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = gray1,
                    unfocusedContainerColor = gray1,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                maxLines = 1
            )

            Spacer(Modifier.height(10.dp))
            Text(text = Strings.password, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 10.dp),
                shape = RoundedCornerShape(21.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = gray1,
                    unfocusedContainerColor = gray1,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                maxLines = 1
            )

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