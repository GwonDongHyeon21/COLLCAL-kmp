package org.collcal.collcal.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.ui.theme.gray12
import org.collcal.collcal.presentation.ui.theme.gray17
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.gray5

@Composable
fun TaskItem(
    task: Pair<Task, Boolean>,
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
        Row(
            modifier = Modifier
                .border(0.5.dp, gray4, RoundedCornerShape(9.38.dp))
                .padding(5.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = task.first.content,
                fontSize = 18.75.sp,
                fontWeight = FontWeight.W500,
                textDecoration = TextDecoration.Underline,
            )
            Text(
                text = task.first.info,
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                color = gray17,
            )
        }
    }
}