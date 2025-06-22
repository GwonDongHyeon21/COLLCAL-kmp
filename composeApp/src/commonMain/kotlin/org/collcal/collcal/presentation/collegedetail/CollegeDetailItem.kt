package org.collcal.collcal.presentation.collegedetail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.component.DownArrowIcon
import org.collcal.collcal.presentation.component.UpArrowIcon
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.transparent
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun CollegeDetailItem(
    task: Task,
    viewModel: CollegeViewModel,
) {
    var taskContentExpanded by remember { mutableStateOf(false) }
    var taskContent by remember { mutableStateOf(task.content) }

    val cardHeight by animateDpAsState(
        targetValue = if (taskContentExpanded) 200.dp else 20.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(2.dp, mainColor),
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = task.title,
                        fontSize = 18.75.sp,
                        fontWeight = FontWeight.W700,
                        textDecoration = TextDecoration.Underline,
                        color = mainColor
                    )
                    Text(
                        text = task.info,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W500,
                        color = mainColor,
                    )
                }

                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = if (taskContentExpanded) UpArrowIcon else DownArrowIcon,
                    contentDescription = "DownArrowIcon",
                    tint = mainColor,
                    modifier = Modifier.clickable(
                        onClick = { taskContentExpanded = !taskContentExpanded },
                        interactionSource = null,
                        indication = null
                    )
                )
            }

            if (taskContentExpanded) {
                Spacer(Modifier.height(10.dp))
                HorizontalDivider(thickness = 2.dp, color = black)

                Spacer(Modifier.height(5.dp))
                Column(modifier = Modifier.height(cardHeight)) {
                    TextField(
                        value = taskContent,
                        onValueChange = { if (it.length < 200) taskContent = it },
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .border(1.dp, black, RoundedCornerShape(3.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = transparent,
                            unfocusedIndicatorColor = transparent,
                            disabledIndicatorColor = transparent,
                            cursorColor = black
                        ),
                        placeholder = {
                            Text(
                                text = Strings.taskContentPlaceholder,
                                fontWeight = FontWeight.W500,
                                color = gray1
                            )
                        }
                    )
                }

                Spacer(Modifier.height(10.dp))
                Button(
                    onClick = {
                        viewModel.modifyTask(task, "", "", taskContent)
                        taskContentExpanded = !taskContentExpanded
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = mainColor)
                ) {
                    Text(text = Strings.save, color = white)
                }
            }
        }
    }
}