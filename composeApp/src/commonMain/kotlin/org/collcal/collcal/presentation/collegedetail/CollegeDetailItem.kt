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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.component.DownArrowIcon
import org.collcal.collcal.presentation.component.UpArrowIcon
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray14
import org.collcal.collcal.presentation.ui.theme.gray17
import org.collcal.collcal.presentation.ui.theme.gray2
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.purple1
import org.collcal.collcal.presentation.ui.theme.transparent

@Composable
fun CollegeDetailItem(
    task: Task,
    viewModel: CollegeDetailViewModel = CollegeDetailViewModel(),
) {
    var taskContentExpanded by remember { mutableStateOf(false) }
    var taskContent by remember { mutableStateOf("") }

    val cardHeight by animateDpAsState(
        targetValue = if (taskContentExpanded) 200.dp else 20.dp,
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(Unit) {
        viewModel.getTaskContent(task.id) { taskContent = it }
    }

    Card(
        shape = RoundedCornerShape(10.03.dp),
        border = BorderStroke(0.53.dp, gray4),
        colors = CardDefaults.cardColors(containerColor = purple1)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
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
                        text = task.content,
                        fontSize = 20.06.sp,
                        fontWeight = FontWeight.W500,
                        textDecoration = TextDecoration.Underline,
                    )
                    Text(
                        text = task.info,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        color = gray17,
                    )
                }

                Spacer(Modifier.weight(1f))
                Icon(
                    modifier = Modifier.clickable(
                        onClick = { taskContentExpanded = !taskContentExpanded },
                        interactionSource = null,
                        indication = null
                    ),
                    imageVector = if (taskContentExpanded) UpArrowIcon else DownArrowIcon,
                    contentDescription = "DownArrowIcon"
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
                            focusedContainerColor = gray2,
                            unfocusedContainerColor = gray2,
                            disabledContainerColor = gray2,
                            focusedIndicatorColor = transparent,
                            unfocusedIndicatorColor = transparent,
                            disabledIndicatorColor = transparent,
                            cursorColor = black
                        ),
                        placeholder = {
                            Text(
                                text = Strings.taskContentPlaceholder,
                                fontWeight = FontWeight.W500,
                                color = gray14
                            )
                        }
                    )
                }

                Spacer(Modifier.height(5.dp))
                Button(onClick = { viewModel.saveTaskContent(taskContent) }) {
                    Text(text = Strings.save)
                }
            }
        }
    }
}