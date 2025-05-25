package org.collcal.collcal.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.PlatformType
import org.collcal.collcal.getPlatformType
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.resources.Strings

@Composable
fun HomeScreen(
    navigator: Navigator,
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth(0.4f)
        PlatformType.ANDROID -> Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        PlatformType.IOS -> Modifier.fillMaxWidth()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = Strings.appName, fontSize = 60.sp)
        Spacer(Modifier.height(40.dp))
        Button(
            onClick = { navigator.navigateTo(Screen.College) },
            modifier = modifier
        ) {
            Text(
                text = Strings.signIn,
                modifier = Modifier.padding(vertical = 20.dp),
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = modifier
        ) {
            Text(
                text = Strings.signUp,
                modifier = Modifier.padding(vertical = 20.dp),
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
    }
}