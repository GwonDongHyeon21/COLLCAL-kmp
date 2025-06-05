package org.collcal.collcal.presentation.collegedetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.sign.component.DownArrowIcon
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.purple1

@Composable
fun CollegeDetailItem(task: String) {
    Card(
        shape = RoundedCornerShape(10.03.dp),
        border = BorderStroke(0.53.dp, gray4),
        colors = CardDefaults.cardColors(containerColor = purple1)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().border(0.5.dp, gray4, RoundedCornerShape(9.38.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = task,
                fontSize = 20.06.sp,
                fontWeight = FontWeight.W500,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(5.dp)
            )
            IconButton(onClick = {}) {
                Icon(imageVector = DownArrowIcon, contentDescription = "DownArrowIcon")
            }
        }
    }
}