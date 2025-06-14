package org.collcal.collcal.presentation.tasks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.component.VCheckIcon
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun TaskAddField(
    taskTitle: String,
    taskInfo: String,
    onTaskTitleChanged: (String) -> Unit,
    onTaskInfoChanged: (String) -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, mainColor, RoundedCornerShape(19.dp)),
        shape = RoundedCornerShape(19.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            BasicTextField(
                value = taskTitle,
                onValueChange = { onTaskTitleChanged(it) },
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .border(0.5.dp, black, RoundedCornerShape(2.dp))
                    .background(white),
                decorationBox = {
                    Box(
                        modifier = Modifier.padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (taskTitle.isEmpty())
                            Text(
                                text = Strings.taskTilte,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                textDecoration = TextDecoration.Underline,
                                color = gray1
                            )
                        it()
                    }
                },
                maxLines = 1
            )
            BasicTextField(
                value = taskInfo,
                onValueChange = { onTaskInfoChanged(it) },
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .border(0.5.dp, black, RoundedCornerShape(2.dp))
                    .background(white),
                decorationBox = {
                    Box(
                        modifier = Modifier.padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (taskTitle.isEmpty())
                            Text(
                                text = Strings.taskInfo,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                color = gray1
                            )
                        it()
                    }
                },
                maxLines = 1
            )

            Icon(
                imageVector = VCheckIcon,
                contentDescription = "VCheckIcon",
                tint = white,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        onClick = { if (taskTitle.isNotEmpty()) onClick() },
                        interactionSource = null,
                        indication = null
                    )
                    .background(mainColor, RoundedCornerShape(2.dp))
            )
        }
    }
}