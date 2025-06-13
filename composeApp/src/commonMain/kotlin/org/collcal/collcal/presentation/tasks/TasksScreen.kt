package org.collcal.collcal.presentation.tasks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.tasks.component.TaskAddField
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray1

@Composable
fun TasksScreen(
    navigator: Navigator,
    viewModel: CollegeViewModel,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onClick: (Task) -> Unit,
) {
    val todos by viewModel.todos.collectAsState()
    val colleges by viewModel.colleges.collectAsState()
    val tasks = colleges.flatMap { it.second.flatMap { task -> task.second } }
    val scheduledTasks = tasks.filter { !it.second }
    val completedTasks = tasks.filter { it.second }

    var isAdd by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }
    var taskInfo by remember { mutableStateOf("") }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth()
        PlatformType.ANDROID -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
        PlatformType.IOS -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = gray1)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = Strings.tasks,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = gray1
                )
                Button(
                    onClick = { isAdd = !isAdd },
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(2.dp, gray1),
                    colors = ButtonDefaults.buttonColors(containerColor = gray1)
                ) {
                    Text(
                        text = "+ ${Strings.Add}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W500,
                        color = gray1
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Text(
                text = Strings.todo,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
            Spacer(Modifier.height(5.dp))
            Column(modifier = Modifier.weight(1f)) {
                if (isAdd) {
                    Row {
                        Spacer(Modifier.width(10.dp))
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
                    }
                    Spacer(Modifier.height(5.dp))
                }

                Spacer(Modifier.height(5.dp))
                if (todos.isNotEmpty())
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        items(todos) { TaskItem(it, viewModel) { onClick(it.first) } }
                    }
            }

            listOf(
                Strings.scheduled to scheduledTasks,
                Strings.completed to completedTasks
            ).forEach { task ->
                Column(modifier = Modifier.weight(1f)) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = task.first,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(Modifier.height(5.dp))
                    if (task.second.isNotEmpty())
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            items(task.second) { TaskItem(it, viewModel) { onClick(it.first) } }
                        }
                }
            }
        }
    }
}