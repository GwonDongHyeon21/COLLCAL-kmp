package org.collcal.collcal.presentation.college

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.collegedetail.CollegeDetailScreen
import org.collcal.collcal.presentation.tasks.TasksScreen
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.transparent
import org.collcal.collcal.presentation.user.UserScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeScreen(
    navigator: Navigator,
    viewModel: CollegeViewModel,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val userInfo by viewModel.userInfo.collectAsState()
    val colleges by viewModel.colleges.collectAsState()
    val todos by viewModel.todos.collectAsState()

    var screen by remember { mutableStateOf<Screen>(Screen.College) }
    val isSelected = remember { mutableStateMapOf<Int, Boolean>() }
    var selectedSemester by remember { mutableStateOf<Int?>(null) }
    var selectedCollegeItem by remember {
        mutableStateOf<Pair<Pair<String, Int>, List<Pair<String, Boolean>>>?>(null)
    }
    var selectedCollegeItemColor by remember { mutableStateOf(transparent) }

    if (isLoading)
        Box(modifier = Modifier.fillMaxSize()) { CircularProgressIndicator() }
    else
        when (getPlatformType()) {
            PlatformType.WEB -> {
                SharedTransitionLayout {
                    Row {
                        AnimatedContent(
                            targetState = screen,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            label = "CollegeTransition"
                        ) { currentScreen ->
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.75f)
                                    .padding(20.dp)
                            ) {
                                when (currentScreen) {
                                    is Screen.College ->
                                        colleges.forEach { college ->
                                            Row(
                                                modifier = Modifier.weight(1f).padding(10.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                                            ) {
                                                Text(
                                                    text = college.first,
                                                    fontSize = 30.sp,
                                                    fontWeight = FontWeight.W800,
                                                )

                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                                ) {
                                                    CollegeItem(
                                                        Modifier.weight(1f),
                                                        college.second,
                                                        userInfo.semesterInt,
                                                        isSelected,
                                                        // @formatter:off
                                                        { selectedSemester = if (isSelected[it] == true) it else null },
                                                        { selectedSemester?.let { int -> viewModel.changeTaskSemester(it, int) } },
                                                        { college, color ->
                                                            selectedCollegeItem = college
                                                            selectedCollegeItemColor = color
                                                            screen = Screen.CollegeDetail
                                                        },
                                                        this@SharedTransitionLayout,
                                                        this@AnimatedContent,
                                                        // @formatter:on
                                                    )
                                                }
                                            }
                                        }

                                    is Screen.CollegeDetail ->
                                        CollegeDetailScreen(
                                            selectedCollegeItem,
                                            selectedCollegeItemColor,
                                            this@SharedTransitionLayout,
                                            this@AnimatedContent
                                        )

                                    else -> {}
                                }
                            }
                        }

                        VerticalDivider(color = gray1)
                        Column(modifier = Modifier.padding(20.dp)) {
                            UserScreen(navigator)
                            Spacer(Modifier.height(10.dp))
                            TasksScreen(navigator, colleges, todos) {
                                // @formatter:off
                                selectedSemester?.let { int -> viewModel.changeTaskSemester(it, int) }
                                // @formatter:on
                            }
                        }
                    }
                }
            }

            PlatformType.ANDROID -> {
                SharedTransitionLayout {
                    AnimatedContent(
                        targetState = screen,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "CollegeTransition"
                    ) { currentScreen ->
                        when (currentScreen) {
                            is Screen.College -> {
                                val pagerState = rememberPagerState(pageCount = { colleges.size })
                                HorizontalPager(state = pagerState) { page ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(innerPadding)
                                            .padding(20.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Text(
                                            text = colleges[page].first,
                                            fontSize = 30.sp,
                                            fontWeight = FontWeight.W800,
                                        )

                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            CollegeItem(
                                                Modifier.weight(1f),
                                                colleges[page].second,
                                                userInfo.semesterInt,
                                                isSelected,
                                                // @formatter:off
                                            { selectedSemester = if (isSelected[it] == true) it else null },
                                            { selectedSemester?.let { int -> viewModel.changeTaskSemester(it, int) } },
                                            { college, color ->
                                                selectedCollegeItem = college
                                                selectedCollegeItemColor = color
                                                screen = Screen.CollegeDetail
                                            },
                                            this@SharedTransitionLayout,
                                            this@AnimatedContent
                                            // @formatter:on
                                            )
                                        }
                                    }
                                }
                            }

                            is Screen.CollegeDetail ->
                                CollegeDetailScreen(
                                    innerPadding,
                                    selectedCollegeItem,
                                    selectedCollegeItemColor,
                                    this@SharedTransitionLayout,
                                    this@AnimatedContent
                                )

                            else -> {}
                        }
                    }
                }
            }

            PlatformType.IOS -> {}
        }
}