package org.collcal.collcal.presentation.taskdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.component.LeftArrowIcon
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun TaskDetailScreen(
    task: Task?,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: TaskDetailViewModel = TaskDetailViewModel(),
    onClick: () -> Unit,
) {
    val taskInfo by viewModel.taskInfo.collectAsState()

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxHeight().fillMaxWidth(0.75f).padding(vertical = 30.dp)
        PlatformType.ANDROID -> Modifier.fillMaxSize().padding(20.dp)
        PlatformType.IOS -> Modifier.fillMaxSize().padding(20.dp)
    }

    val size = when (getPlatformType()) {
        PlatformType.WEB -> 40.dp
        PlatformType.ANDROID -> 20.dp
        PlatformType.IOS -> 20.dp
    }

    LaunchedEffect(Unit) { viewModel.getTaskInfo(task) }

    Card(
        modifier = modifier
            .padding(innerPadding)
            .shadow(5.dp, RoundedCornerShape(21.dp)),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = white),
        border = BorderStroke(2.dp, mainColor)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(size)) {
            Row {
                Icon(
                    imageVector = LeftArrowIcon,
                    contentDescription = "ArrowBackIcon",
                    tint = white,
                    modifier = Modifier
                        .clickable(
                            onClick = { onClick() },
                            interactionSource = null,
                            indication = null
                        )
                        .size(36.dp)
                        .background(mainColor, RoundedCornerShape(12.dp))
                )

                Spacer(Modifier.width((size * 2 / 3)))
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = task?.title ?: "",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W700,
                        textDecoration = TextDecoration.Underline,
                        color = mainColor
                    )
                    Text(
                        text = task?.info ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        color = mainColor,
                    )
                }
            }

            taskInfo?.let { info ->
                Spacer(Modifier.height(size * 2))
                Row {
                    Text(text = "- ", fontSize = 15.sp)
                    Text(text = info.task, fontSize = 15.sp)
                }
                Spacer(Modifier.height(10.dp))
                Row {
                    Text(text = "- ", fontSize = 15.sp)
                    Text(text = info.year, fontSize = 15.sp)
                }
                Spacer(Modifier.height(10.dp))
                Row {
                    Text(text = "- ", fontSize = 15.sp)
                    Column {
                        info.link.forEach {
                            Text(text = it, fontSize = 15.sp)
                            Spacer(Modifier.height(5.dp))
                        }
                    }
                }
            }
        }
    }
}