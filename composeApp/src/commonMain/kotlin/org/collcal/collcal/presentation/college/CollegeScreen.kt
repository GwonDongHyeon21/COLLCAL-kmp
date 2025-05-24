package org.collcal.collcal.presentation.college

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.collcal.collcal.PlatformType
import org.collcal.collcal.getPlatformType
import org.collcal.collcal.platform.getScreenWidthDp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen

@Composable
fun CollegeScreen(
    navigator: Navigator,
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    val platformType = getPlatformType()
    val colleges = listOf(listOf("1", "1-1", "1-2", "1-3"), listOf("2", "2-1", "2-2", "2-3"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (platformType) {
            PlatformType.WEB -> {
                Row {
                    colleges.forEach { college ->
                        Card(modifier = Modifier.weight(1f).padding(10.dp)) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                college.forEach { CollegeItem(it) }
                            }
                        }
                    }
                }
            }

            PlatformType.ANDROID -> {
                val pagerState = rememberPagerState(pageCount = { colleges.size })

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    Card(
                        modifier = Modifier
                            .width(getScreenWidthDp().dp)
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            colleges[page].forEach { college -> CollegeItem(college) }
                        }
                    }
                }
            }

            PlatformType.IOS -> {}
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigator.replaceTo(Screen.User) }) {
            Text(text = "replace to User")
        }
    }
}