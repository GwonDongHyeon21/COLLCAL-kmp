package org.collcal.collcal.presentation.college

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
    collegeItem: List<Triple<String, Int, List<String>>>,
    semesterInt: Int,
) {
    collegeItem.forEach { college ->
        Card(
            modifier = modifier.fillMaxSize(),
            shape = RoundedCornerShape(7.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (college.second < semesterInt) blue1
                else if (college.second == semesterInt) blue2
                else gray3
            )
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = college.first,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    color = if (college.second == semesterInt) blue3 else black
                )

                Spacer(Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    college.second.forEach { taskItem(it) }
                }
            }
        }
    }
}