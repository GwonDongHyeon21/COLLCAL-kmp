package org.collcal.collcal.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.tasks.component.TaskAddField
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.blue1
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray3
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun TasksScreen(
    navigator: Navigator,
    viewModel: CollegeViewModel,
    onClick: (Task) -> Unit,
    onClickTaskText: (Task) -> Unit,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val todos by viewModel.todos.collectAsState()
    val colleges by viewModel.colleges.collectAsState()
    val userInfo by viewModel.userInfo.collectAsState()
    val tasks = colleges.flatMap { it.second.flatMap { task -> task.second } }
    val scheduledTasks = tasks.filter { it.status >= (userInfo.semesterInt ?: 0) }
    val completedTasks = tasks.filter { it.status < (userInfo.semesterInt ?: 0) }

    var isAdd by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }
    var taskInfo by remember { mutableStateOf("") }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth()
        PlatformType.ANDROID -> Modifier
            .fillMaxSize()
            .background(blue1)
            .padding(innerPadding)
            .padding(20.dp)

        PlatformType.IOS -> Modifier
            .fillMaxSize()
            .background(blue1)
            .padding(innerPadding)
            .padding(20.dp)
    }

    Card(
        modifier = modifier
            .padding(vertical = 5.dp)
            .shadow(5.dp, RoundedCornerShape(7.dp)),
        shape = RoundedCornerShape(7.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = Strings.tasks,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = mainColor
                )
                Card(
                    onClick = { isAdd = !isAdd },
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = mainColor),
                ) {
                    Text(
                        text = "+ ${Strings.Add}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W500,
                        color = white,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                }
            }

            HorizontalDivider(color = gray1, modifier = Modifier.padding(bottom = 10.dp))

            Text(
                text = Strings.todo,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
            Spacer(Modifier.height(5.dp))
            Column(
                modifier = when (getPlatformType()) {
                    PlatformType.WEB -> Modifier.height(100.dp)
                    PlatformType.ANDROID -> Modifier.weight(1f)
                    PlatformType.IOS -> Modifier.weight(1f)
                }
                    .fillMaxWidth()
                    .background(gray3, RoundedCornerShape(7.dp))
                    .padding(5.dp)
            ) {
                if (isAdd) {
                    TaskAddField(
                        taskTitle = taskTitle,
                        taskInfo = taskInfo,
                        onTaskTitleChanged = { taskTitle = it },
                        onTaskInfoChanged = { taskInfo = it }
                    ) {
                        viewModel.addTask(taskTitle, taskInfo)
                        isAdd = !isAdd
                        taskTitle = ""
                        taskInfo = ""
                    }
                    Spacer(Modifier.height(5.dp))
                }
                if (todos.isNotEmpty())
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        items(todos) {
                            TaskItem(
                                it,
                                viewModel,
                                { onClick(it) },
                                { onClickTaskText(it) }
                            )
                        }
                    }
            }

            listOf(
                Strings.scheduled to scheduledTasks,
                Strings.completed to completedTasks
            ).forEach { task ->
                Spacer(Modifier.height(10.dp))
                Text(
                    text = task.first,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(5.dp))
                Column(
                    modifier = when (getPlatformType()) {
                        PlatformType.WEB -> Modifier.height(100.dp)
                        PlatformType.ANDROID -> Modifier.weight(1f)
                        PlatformType.IOS -> Modifier.weight(1f)
                    }
                        .fillMaxWidth()
                        .background(gray3, RoundedCornerShape(7.dp))
                        .padding(5.dp)
                ) {
                    if (task.second.isNotEmpty())
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            items(task.second) {
                                TaskItem(
                                    it,
                                    viewModel,
                                    { onClick(it) },
                                    { onClickTaskText(it) }
                                )
                            }
                        }
                }
            }
        }
    }
}