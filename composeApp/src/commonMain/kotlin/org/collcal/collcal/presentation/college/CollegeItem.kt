package org.collcal.collcal.presentation.college

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.component.RightArrowIcon
import org.collcal.collcal.presentation.tasks.TaskItem
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeItem(
    modifier: Modifier,
    viewModel: CollegeViewModel,
    collegeItem: List<Pair<Pair<String, Int>, List<Pair<Task, Boolean>>>>,
    userSemesterInt: Int,
    isSelected: SnapshotStateMap<Int, Boolean>,
    onClick: (Int) -> Unit,
    onClickTask: (Task) -> Unit,
    onClickZoomIn: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    collegeItem.forEach { college ->
        val semesterInt = college.first.second
        val selectedState = isSelected[semesterInt] ?: false

        with(sharedTransitionScope) {
            Card(
                modifier = modifier
                    .fillMaxSize()
                    .shadow(10.dp, RoundedCornerShape(21.dp))
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(
                            key = "college-$semesterInt"
                        ),
                        animatedVisibilityScope = animatedContentScope
                    ),
                shape = RoundedCornerShape(21.dp),
                border = if (isSelected[semesterInt] == true) BorderStroke(
                    2.dp,
                    mainColor
                ) else null
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            onClick = {
                                isSelected.keys.forEach { isSelected[it] = false }
                                isSelected[semesterInt] = !selectedState
                                onClick(semesterInt)
                            },
                            interactionSource = null,
                            indication = null
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    white,
                                    if (userSemesterInt == semesterInt) mainColor.copy(0.4f) else white
                                )
                            ),
                            shape = RoundedCornerShape(21.dp)
                        )
                        .clip(RoundedCornerShape(21.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = college.first.first,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W700,
                        )
                        IconButton(
                            onClick = {
                                onClickZoomIn(semesterInt)
                                isSelected.keys.forEach { isSelected[it] = false }
                            },
                            modifier = Modifier.size(20.dp),
                            colors = IconButtonDefaults.iconButtonColors(containerColor = mainColor)
                        ) {
                            Icon(
                                imageVector = RightArrowIcon,
                                contentDescription = "RightArrowIcon",
                                tint = white
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))
                    HorizontalDivider(color = gray1)

                    Spacer(Modifier.height(10.dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        items(college.second) { TaskItem(it, viewModel) { onClickTask(it.first) } }
                    }
                }
            }
        }
    }
}