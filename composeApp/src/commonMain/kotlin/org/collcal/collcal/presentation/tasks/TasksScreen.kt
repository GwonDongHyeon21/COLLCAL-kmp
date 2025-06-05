package org.collcal.collcal.presentation.tasks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.component.TaskItem
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray10
import org.collcal.collcal.presentation.ui.theme.gray11
import org.collcal.collcal.presentation.ui.theme.gray8
import org.collcal.collcal.presentation.ui.theme.gray9
import org.collcal.collcal.presentation.ui.theme.transparent

@Composable
fun TasksScreen(
    navigator: Navigator,
    colleges: List<Pair<String, List<Pair<Pair<String, Int>, List<Pair<String, Boolean>>>>>>,
    todos: List<Pair<String, Boolean>>,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onClick: (Pair<String, Boolean>) -> Unit,
) {
    val tasks = colleges.flatMap { it.second.flatMap { task -> task.second } }
    val scheduledTasks = tasks.filter { !it.second }
    val completedTasks = tasks.filter { it.second }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth()
        PlatformType.ANDROID -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
        PlatformType.IOS -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = gray8)
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
                    color = gray9,
                )
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(2.dp, gray10),
                    colors = ButtonDefaults.buttonColors(containerColor = transparent)
                ) {
                    Text(
                        text = "+ ${Strings.add}",
                        fontSize = 15.sp,
                        color = gray11,
                        fontWeight = FontWeight.W500
                    )
                }
            }

            listOf(
                "To Do" to todos,
                "Scheduled" to scheduledTasks,
                "Completed" to completedTasks
            ).forEach { task ->
                Spacer(Modifier.height(10.dp))
                Text(
                    text = task.first,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(Modifier.height(5.dp))
                if (task.second.isNotEmpty())
                    FlowRow(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        task.second.forEach { TaskItem(it) { onClick(it) } }
                    }
            }
        }
    }
}