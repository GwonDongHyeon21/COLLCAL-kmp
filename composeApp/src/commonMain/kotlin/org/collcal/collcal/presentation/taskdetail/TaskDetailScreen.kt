package org.collcal.collcal.presentation.taskdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import org.collcal.collcal.presentation.ui.theme.blue2
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
    val fontSize = when (getPlatformType()) {
        PlatformType.WEB -> 20.sp
        PlatformType.ANDROID -> 15.sp
        PlatformType.IOS -> 15.sp
    }

    LaunchedEffect(task) { viewModel.getTaskInfo(task) }

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
                Column(
                    modifier = Modifier
                        .padding(horizontal = size)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(size))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(mainColor, RoundedCornerShape(24.dp))
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        info.task.forEach {
                            Row {
                                Text(text = "- ", fontSize = fontSize, color = white)
                                Text(text = it, fontSize = fontSize, color = white)
                            }
                        }
                    }

                    Spacer(Modifier.height(size * 3 / 4))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                            .background(mainColor, RoundedCornerShape(24.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(white, RoundedCornerShape(24.dp))
                                .border(2.dp, mainColor, RoundedCornerShape(24.dp))
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "적정시기", fontSize = fontSize)
                        }
                        Text(
                            text = info.year,
                            fontSize = fontSize,
                            color = white,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                        )
                    }

                    Spacer(Modifier.height(size * 3 / 4))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                            .background(mainColor, RoundedCornerShape(24.dp)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(white, RoundedCornerShape(24.dp))
                                .border(2.dp, mainColor, RoundedCornerShape(24.dp))
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "관련 링크 ", fontSize = fontSize)
                        }
                        Column {
                            info.link.forEach {
                                CompositionLocalProvider(
                                    value = LocalTextSelectionColors provides TextSelectionColors(
                                        handleColor = mainColor,
                                        backgroundColor = blue2
                                    )
                                ) {
                                    SelectionContainer {
                                        Text(
                                            text = it,
                                            fontSize = fontSize,
                                            color = white,
                                            modifier = Modifier.padding(
                                                vertical = 10.dp,
                                                horizontal = 20.dp
                                            )
                                        )
                                    }
                                }
                                Spacer(Modifier.height(5.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}