package org.collcal.collcal.presentation.collegedetail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.component.LeftArrowIcon
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.blue2
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeDetailScreen(
    viewModel: CollegeViewModel,
    colleges: List<Pair<String, List<Pair<Pair<String, Int>, List<Task>>>>>,
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

    val size = when (getPlatformType()) {
        PlatformType.WEB -> 40.dp
        PlatformType.ANDROID -> 20.dp
        PlatformType.IOS -> 20.dp
    }

    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .shadow(5.dp, RoundedCornerShape(21.dp))
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = "college-$semesterInt"
                    ),
                    animatedVisibilityScope = animatedContentScope
                ),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = white),
            border = BorderStroke(2.dp, mainColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                white,
                                if (userSemesterInt == semesterInt) blue2 else white
                            )
                        ),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .padding(size)
            ) {
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
                LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    item {
                        Text(
                            text = tasks?.first?.first ?: "",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W600,
                        )
                    }
                    item {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = size / 2),
                            color = black
                        )
                    }
                    tasks?.let { college ->
                        items(college.second) { CollegeDetailItem(it, viewModel) }
                    }
                }
            }
        }
    }
}