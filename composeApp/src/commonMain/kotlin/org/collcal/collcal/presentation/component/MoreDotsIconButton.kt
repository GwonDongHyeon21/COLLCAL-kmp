package org.collcal.collcal.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.ui.theme.gray13

@Composable
fun MoreDotsIconButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable(
            onClick = { onClick() },
            interactionSource = null,
            indication = null
        )
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(gray13)
        )
        Spacer(Modifier.width(2.dp))
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(gray13)
        )
        Spacer(Modifier.width(2.dp))
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(gray13)
        )
    }
}