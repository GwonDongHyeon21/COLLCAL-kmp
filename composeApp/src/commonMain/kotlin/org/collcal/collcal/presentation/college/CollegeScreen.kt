package org.collcal.collcal.presentation.college

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.collegedetail.CollegeDetailScreen
import org.collcal.collcal.presentation.component.LoadingScreen
import org.collcal.collcal.presentation.taskdetail.TaskDetailScreen
import org.collcal.collcal.presentation.tasks.TasksScreen
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.ui.theme.blue1
import org.collcal.collcal.presentation.ui.theme.gray2
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.user.UserScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollegeScreen(
    navigator: Navigator,
    viewModel: CollegeViewModel,
    onClickTaskText: (Task) -> Unit,
    onSignOut: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val userInfo by viewModel.userInfo.collectAsState()
    val colleges by viewModel.colleges.collectAsState()
    val userSemesterInt = userInfo.semesterInt ?: 0

    var screen by remember { mutableStateOf<Screen>(Screen.College) }
    val isSelected = remember { mutableStateMapOf<Int, Boolean>() }
    var selectedSemester by remember { mutableStateOf<Int?>(null) }
    var selectedSemesterForDetail by remember { mutableStateOf<Int?>(null) }
    var isTaskDetail by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    var isGetTasks by remember { mutableStateOf(false) }
    var isGetUser by remember { mutableStateOf(false) }
    var isGetCredits by remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTasks { isGetTasks = true }
        viewModel.getUser { if (it) isGetUser = it else navigator.replaceTo(Screen.SignIn) }
        viewModel.getCredits { isGetCredits = true }
    }
    LaunchedEffect(isGetTasks, isGetUser, isGetCredits) {
        if (listOf(isGetTasks, isGetUser, isGetCredits).all { it })
            viewModel.loadingState(false)
    }

    if (isLoading)
        LoadingScreen()
    else
        when (getPlatformType()) {
            PlatformType.WEB -> {
                SharedTransitionLayout {
                    Row(
                        modifier = Modifier
                            .background(blue1)
                            .padding(horizontal = 60.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        if (isTaskDetail)
                            TaskDetailScreen(selectedTask) { isTaskDetail = false }
                        else
                            AnimatedContent(
                                targetState = screen,
                                transitionSpec = { fadeIn() togetherWith fadeOut() },
                                label = "CollegeTransition"
                            ) { currentScreen ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(0.75f)
                                        .padding(vertical = 30.dp),
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    when (currentScreen) {
                                        is Screen.College ->
                                            colleges.forEach { college ->
                                                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxSize()
                                                            .padding(25.dp)
                                                            .background(mainColor)
                                                    )
                                                    Row {
                                                        CollegeItem(
                                                            Modifier.weight(1f),
                                                            viewModel,
                                                            college.second,
                                                            userSemesterInt,
                                                            isSelected,
                                                            // @formatter:off
                                                        { selectedSemester = if (isSelected[it] == true) it else null },
                                                        { selectedSemester?.let { int -> viewModel.changeTaskSemester(it, int) } },
                                                        {
                                                            selectedSemesterForDetail = it
                                                            screen = Screen.CollegeDetail
                                                        },
                                                        {
                                                            selectedTask = it
                                                            isTaskDetail = true
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
                                                viewModel,
                                                colleges,
                                                userSemesterInt,
                                                selectedSemesterForDetail,
                                                this@SharedTransitionLayout,
                                                this@AnimatedContent
                                            ) {
                                                screen = Screen.College
                                            }

                                        else -> {}
                                    }
                                }
                            }

                        VerticalDivider(
                            color = gray2,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            UserScreen(navigator, viewModel) { onSignOut() }
                            TasksScreen(
                                // @formatter:off
                                navigator,
                                viewModel,
                                { selectedSemester?.let { int -> viewModel.changeTaskSemester(it, int) } },
                                {
                                    selectedTask = it
                                    isTaskDetail = true
                                }
                                // @formatter:on
                            )
                        }
                    }
                }
            }

            PlatformType.ANDROID -> {
                val pagerState = rememberPagerState(
                    pageCount = { colleges.size },
                    initialPage = userSemesterInt / colleges.size
                )
                SharedTransitionLayout {
                    AnimatedContent(
                        targetState = screen,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "CollegeTransition"
                    ) { currentScreen ->
                        when (currentScreen) {
                            is Screen.College -> {
                                HorizontalPager(state = pagerState) { page ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(blue1)
                                            .padding(innerPadding)
                                            .padding(20.dp)
                                    ) {
                                        CollegeItem(
                                            Modifier.weight(1f),
                                            viewModel,
                                            colleges[page].second,
                                            userSemesterInt,
                                            isSelected,
                                            // @formatter:off
                                            { selectedSemester = if (isSelected[it] == true) it else null },
                                            { selectedSemester?.let { int -> viewModel.changeTaskSemester(it, int) } },
                                            {
                                                selectedSemesterForDetail = it
                                                screen = Screen.CollegeDetail
                                            },
                                            { onClickTaskText(it) },
                                            this@SharedTransitionLayout,
                                            this@AnimatedContent
                                            // @formatter:on
                                        )
                                    }
                                }
                            }

                            is Screen.CollegeDetail ->
                                Box(modifier = Modifier.padding(20.dp)) {
                                    CollegeDetailScreen(
                                        viewModel,
                                        colleges,
                                        userSemesterInt,
                                        selectedSemesterForDetail,
                                        this@SharedTransitionLayout,
                                        this@AnimatedContent,
                                        innerPadding
                                    ) {
                                        screen = Screen.College
                                    }
                                }

                            else -> {}
                        }
                    }
                }
            }

            PlatformType.IOS -> {}
        }
}