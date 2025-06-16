package org.collcal.collcal.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.collcal.collcal.navigation.AndroidNavigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun BottomBar(
    items: List<Triple<String, ImageVector, Screen>>,
    navigator: AndroidNavigator,
    tabScreen: String,
) {
    Surface(
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBar(containerColor = white) {
            items.forEach {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = it.second,
                            contentDescription = it.first
                        )
                    },
                    label = { Text(text = it.first) },
                    selected = tabScreen == it.third.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = mainColor,
                        selectedTextColor = mainColor
                    ),
                    onClick = {
                        navigator.resetAndNavigateTo(
                            items.first().third,
                            it.third
                        )
                    }
                )
            }
        }
    }
}