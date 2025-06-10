package org.collcal.collcal.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.component.CustomDropDown
import org.collcal.collcal.presentation.component.MoreDotsIconButton
import org.collcal.collcal.presentation.tasks.component.TaskAddField
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray12
import org.collcal.collcal.presentation.ui.theme.gray17
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.gray5

@Composable
fun TaskItem(
    task: Pair<Task, Boolean>,
    viewModel: CollegeViewModel,
    onClick: () -> Unit,
) {
    var moreActionExpanded by remember { mutableStateOf(false) }
    var isModify by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf(task.first.content) }
    var taskInfo by remember { mutableStateOf(task.first.info) }

    if (isModify)
        TaskAddField(
            taskTitle = taskTitle,
            taskInfo = taskInfo,
            onTaskTitleChanged = { taskTitle = it },
            onTaskInfoChanged = { taskInfo = it },
            color = if (task.second) gray12 else gray5
        ) {
            viewModel.modifyTask(task.first.id, taskTitle, taskInfo)
            isModify = !isModify
        }
    else
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { onClick() },
                    interactionSource = null,
                    indication = null
                )
                .border(0.5.dp, gray4, RoundedCornerShape(9.38.dp))
                .background(if (task.second) gray12 else gray5, RoundedCornerShape(9.38.dp))
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
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

            Spacer(Modifier.weight(1f))
            Column {
                MoreDotsIconButton { moreActionExpanded = !moreActionExpanded }
                CustomDropDown(
                    options = listOf(Strings.moveToTodo, Strings.modify, Strings.delete),
                    isExpanded = moreActionExpanded,
                    onClickExpanded = { moreActionExpanded = !moreActionExpanded },
                    onClickOption = {
                        when (it) {
                            Strings.moveToTodo -> viewModel.moveToTodoTask(task.first)
                            Strings.modify -> isModify = !isModify
                            Strings.delete -> viewModel.deleteTask(task.first.id)
                        }
                    }
                )
            }
        }
}