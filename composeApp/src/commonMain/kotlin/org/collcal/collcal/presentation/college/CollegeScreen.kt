package org.collcal.collcal.presentation.college

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.tasks.TasksScreen
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.user.UserScreen

@Composable
fun CollegeScreen(
    navigator: Navigator,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: CollegeViewModel = CollegeViewModel(),
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val userInfo by viewModel.userInfo.collectAsState()
    val colleges by viewModel.colleges.collectAsState()

    LaunchedEffect(Unit) { viewModel.getCollegeData() }

    if (isLoading)
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    else
        when (getPlatformType()) {
            PlatformType.WEB -> {
                Row {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.75f)
                            .padding(20.dp)
                    ) {
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
                                        userInfo.semesterInt
                                    )
                                }
                            }
                        }
                    }

                    VerticalDivider(color = gray1)
                    Column(modifier = Modifier.padding(20.dp)) {
                        UserScreen(navigator)
                        Spacer(Modifier.height(10.dp))
                        TasksScreen(navigator)
                    }
                }
            }

            PlatformType.ANDROID -> {
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
                                userInfo.semesterInt
                            )
                        }
                    }
                }
            }

            PlatformType.IOS -> {}
        }
}