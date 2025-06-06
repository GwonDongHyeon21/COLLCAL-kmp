package org.collcal.collcal.presentation.college

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.component.TaskItem
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.blue1
import org.collcal.collcal.presentation.ui.theme.blue2
import org.collcal.collcal.presentation.ui.theme.blue3
import org.collcal.collcal.presentation.ui.theme.gray3

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeItem(
    modifier: Modifier,
    collegeItem: List<Pair<Pair<String, Int>, List<Pair<String, Boolean>>>>,
    userSemesterInt: Int,
    isSelected: SnapshotStateMap<Int, Boolean>,
    onClick: (Int) -> Unit,
    onClickTask: (Pair<String, Boolean>) -> Unit,
    onClickZoomIn: (Int, Color) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    collegeItem.forEach { college ->
        val semesterInt = college.first.second
        val selectedState = isSelected[semesterInt] ?: false
        val collegeColor = when {
            semesterInt < userSemesterInt -> blue1
            semesterInt == userSemesterInt -> blue2
            else -> gray3
        }

        with(sharedTransitionScope) {
            Card(
                modifier = modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                isSelected.keys.forEach { isSelected[it] = false }
                                isSelected[semesterInt] = !selectedState
                                onClick(semesterInt)
                            },
                            onDoubleTap = {
                                onClickZoomIn(semesterInt, collegeColor)
                                isSelected.keys.forEach { isSelected[it] = false }
                            }
                        )
                    }
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(
                            key = "college-$semesterInt"
                        ),
                        animatedVisibilityScope = animatedContentScope
                    ),
                shape = RoundedCornerShape(7.dp),
                colors = CardDefaults.cardColors(containerColor = collegeColor),
                border = if (isSelected[semesterInt] == true) BorderStroke(3.dp, blue2) else null
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = college.first.first,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W700,
                        color = if (semesterInt == userSemesterInt) blue3 else black
                    )

                    Spacer(Modifier.height(10.dp))
                    FlowRow(
                        modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        college.second.forEach { TaskItem(it) { onClickTask(it) } }
                    }
                }
            }
        }
    }
}