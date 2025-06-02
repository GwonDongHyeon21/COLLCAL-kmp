package org.collcal.collcal.presentation.college

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.component.taskItem
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.blue1
import org.collcal.collcal.presentation.ui.theme.blue2
import org.collcal.collcal.presentation.ui.theme.blue3
import org.collcal.collcal.presentation.ui.theme.gray3

@Composable
fun CollegeItem(
    modifier: Modifier,
    collegeItem: List<Pair<Pair<String, Int>, List<Pair<String, Boolean>>>>,
    userSemesterInt: Int,
    isSelected: SnapshotStateMap<Int, Boolean>,
    onClick: (Int) -> Unit,
    onClickTask: (Pair<String, Boolean>) -> Unit,
) {
    collegeItem.forEach { college ->
        val semesterInt = college.first.second
        val selectedState = isSelected[semesterInt] ?: false
        Card(
            modifier = modifier
                .fillMaxSize()
                .clickable(interactionSource = null, indication = null) {
                    isSelected.keys.forEach { isSelected[it] = false }
                    isSelected[semesterInt] = !selectedState
                    onClick(semesterInt)
                },
            shape = RoundedCornerShape(7.dp),
            colors = CardDefaults.cardColors(
                containerColor = when {
                    semesterInt < userSemesterInt -> blue1
                    semesterInt == userSemesterInt -> blue2
                    else -> gray3
                }
            ),
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
                    college.second.forEach { taskItem(it) { onClickTask(it) } }
                }
            }
        }
    }
}