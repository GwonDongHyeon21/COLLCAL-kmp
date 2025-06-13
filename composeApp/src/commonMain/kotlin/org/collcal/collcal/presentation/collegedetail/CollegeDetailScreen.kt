package org.collcal.collcal.presentation.collegedetail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.component.ArrowBackIcon
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeDetailScreen(
    colleges: List<Pair<String, List<Pair<Pair<String, Int>, List<Pair<Task, Boolean>>>>>>,
    userSemesterInt: Int,
    semesterInt: Int?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit,
) {
    val tasks by remember {
        mutableStateOf(colleges.flatMap { it.second }.find { it.first.second == semesterInt })
    }

    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .shadow(10.dp, RoundedCornerShape(21.dp))
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = "college-$semesterInt"
                    ),
                    animatedVisibilityScope = animatedContentScope
                ),
            shape = RoundedCornerShape(21.dp),
            colors = CardDefaults.cardColors(containerColor = white)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onClick() }) {
                        Icon(imageVector = ArrowBackIcon, contentDescription = "ArrowBackIcon")
                    }
                    Text(
                        text = tasks?.first?.first ?: "",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.W700,
                    )
                }

                Spacer(Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    tasks?.let { college ->
                        items(college.second) { CollegeDetailItem(it.first) }
                    }
                }
            }
        }
    }
}