package org.collcal.collcal.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.mainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    Surface(
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(bottomStart = 19.dp, bottomEnd = 19.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = Strings.appName,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W800,
                    color = mainColor
                )
            }
        )
    }
}