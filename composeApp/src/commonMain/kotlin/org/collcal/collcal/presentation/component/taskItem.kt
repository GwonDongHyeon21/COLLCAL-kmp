package org.collcal.collcal.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.ui.theme.gray12
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.gray5

@Composable
fun TaskItem(
    task: Pair<String, Boolean>,
    onClick: () -> Unit,
) {
    Card(
        onClick = { onClick() },
        shape = RoundedCornerShape(9.38.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.second) gray12 else gray5,
            disabledContainerColor = if (task.second) gray12 else gray5
        )
    ) {
        Text(
            text = task.first,
            fontSize = 18.75.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.border(0.5.dp, gray4, RoundedCornerShape(9.38.dp)).padding(5.dp)
        )
    }
}