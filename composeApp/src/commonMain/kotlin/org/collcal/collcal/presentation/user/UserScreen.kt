package org.collcal.collcal.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.blue1
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray3
import org.collcal.collcal.presentation.ui.theme.mainColor

@Composable
fun UserScreen(
    navigator: Navigator,
    viewModel: CollegeViewModel,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val userInfo by viewModel.userInfo.collectAsState()
    val earnedCredit by viewModel.earnedCredit.collectAsState()
    val averageCredit by viewModel.averageCredit.collectAsState()
    val courses = viewModel.courses.collectAsState()

    val majorBasicExpanded = remember { mutableStateOf(false) }
    val majorRequiredExpanded = remember { mutableStateOf(false) }
    val majorElectiveExpanded = remember { mutableStateOf(false) }
    val requiredLiberalArtsExpanded = remember { mutableStateOf(false) }
    val distributedExpanded = remember { mutableStateOf(false) }
    val freeComplementExpanded = remember { mutableStateOf(false) }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth()
        PlatformType.ANDROID -> Modifier
            .fillMaxSize()
            .background(blue1)
            .padding(innerPadding)
            .padding(20.dp)

        PlatformType.IOS -> Modifier
            .fillMaxSize()
            .background(blue1)
            .padding(innerPadding)
            .padding(20.dp)
    }

    Card(
        modifier = modifier
            .padding(vertical = 5.dp)
            .shadow(5.dp, RoundedCornerShape(7.dp)),
        shape = RoundedCornerShape(7.dp),
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = userInfo.department,
                fontSize = 20.sp,
                fontWeight = FontWeight.W800,
                color = mainColor
            )
            Text(
                text = userInfo.semester,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )

            HorizontalDivider(color = gray1, modifier = Modifier.padding(vertical = 5.dp))

            Card(
                shape = RoundedCornerShape(7.dp),
                colors = CardDefaults.cardColors(containerColor = gray3)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf(
                        Strings.earnedCredits to earnedCredit,
                        Strings.averageCredit to averageCredit
                    ).forEach {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = it.first,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W800
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = it.second.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W300
                            )
                        }
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf(
                    Triple(
                        "전공 기초",
                        Triple(
                            courses.value[0].sumOf { it.credit },
                            userInfo.majorRequiredCredits,
                            majorBasicExpanded
                        ),
                        courses.value[0]
                    ),
                    Triple(
                        "전공 필수",
                        Triple(
                            courses.value[1].sumOf { it.credit },
                            userInfo.majorRequiredCredits,
                            majorRequiredExpanded
                        ),
                        courses.value[1]
                    ),
                    Triple(
                        "전공 선택",
                        Triple(
                            courses.value[2].sumOf { it.credit },
                            userInfo.majorElectiveCredits,
                            majorElectiveExpanded
                        ),
                        courses.value[2]
                    ),
                    Triple(
                        "필수 교과",
                        Triple(
                            courses.value[3].sumOf { it.credit },
                            userInfo.requiredLiberalArtsCredits,
                            requiredLiberalArtsExpanded
                        ),
                        courses.value[3]
                    ),
                    Triple(
                        "배분 이수",
                        Triple(
                            courses.value[4].sumOf { it.credit },
                            userInfo.distributedCredits,
                            distributedExpanded
                        ),
                        courses.value[4]
                    ),
                    Triple(
                        "자유 이수",
                        Triple(
                            courses.value[5].sumOf { it.credit },
                            userInfo.distributedCredits,
                            freeComplementExpanded
                        ),
                        courses.value[5]
                    )
                ).forEachIndexed { index, creditInfo ->
                    UserItem(
                        creditInfo = creditInfo,
                        onAddCredit = { viewModel.addCredit(index, it) },
                        onModifyCredit = { viewModel.modifyCredit(index, it) },
                        onDeleteCredit= { viewModel.deleteCredit(index, it) }
                    )
                }
            }
        }
    }
}