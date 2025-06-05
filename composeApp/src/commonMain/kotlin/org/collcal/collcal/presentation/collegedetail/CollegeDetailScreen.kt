package org.collcal.collcal.presentation.collegedetail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.component.taskItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeDetailScreen(
    selectedCollegeItem: Pair<Pair<String, Int>, List<Pair<String, Boolean>>>?,
    selectedCollegeItemColor: Color,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = "college-${selectedCollegeItem?.first?.second}"
                    ),
                    animatedVisibilityScope = animatedContentScope
                ),
            shape = RoundedCornerShape(7.dp),
            colors = CardDefaults.cardColors(containerColor = selectedCollegeItemColor)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = selectedCollegeItem?.first?.first ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                )

                Spacer(Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    selectedCollegeItem?.second?.forEach { taskItem(it) {} }
                }
            }
        }
    }
}