package org.collcal.collcal.presentation.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.platform.PlatformType
import org.collcal.collcal.platform.getPlatformType
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun UserScreen(
    navigator: Navigator,
    viewModel: CollegeViewModel,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val userInfo by viewModel.userInfo.collectAsState()
    val earnedCredit by viewModel.earnedCredit.collectAsState()
    val averageCredit by viewModel.averageCredit.collectAsState()
    val aList by viewModel.aList.collectAsState()
    val bList by viewModel.bList.collectAsState()
    val cList by viewModel.cList.collectAsState()
    val dList by viewModel.dList.collectAsState()

    val majorRequiredExpanded = remember { mutableStateOf(false) }
    val majorElectiveExpanded = remember { mutableStateOf(false) }
    val requiredLiberalArtsExpanded = remember { mutableStateOf(false) }
    val distributedExpanded = remember { mutableStateOf(false) }

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth()
        PlatformType.ANDROID -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
        PlatformType.IOS -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(containerColor = gray1)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = userInfo.department,
                fontSize = 20.sp,
                fontWeight = FontWeight.W800
            )
            Text(
                text = userInfo.semester,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )

            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(containerColor = white)
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

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                listOf(
                    Triple(
                        "전공필수",
                        Triple(
                            aList.sumOf { it.credit.toInt() },
                            userInfo.majorRequiredCredits,
                            majorRequiredExpanded
                        ),
                        aList
                    ),
                    Triple(
                        "전공선택",
                        Triple(
                            bList.sumOf { it.credit.toInt() },
                            userInfo.majorElectiveCredits,
                            majorElectiveExpanded
                        ),
                        bList
                    ),
                    Triple(
                        "필수교양",
                        Triple(
                            cList.sumOf { it.credit.toInt() },
                            userInfo.requiredLiberalArtsCredits,
                            requiredLiberalArtsExpanded
                        ),
                        cList
                    ),
                    Triple(
                        "배분이수",
                        Triple(
                            dList.sumOf { it.credit.toInt() },
                            userInfo.distributedCredits,
                            distributedExpanded
                        ),
                        dList
                    )
                ).forEachIndexed { index, creditInfo ->
                    UserItem(
                        creditInfo = creditInfo,
                        onAddCredit = { viewModel.addCredit(index, it) },
                        onModifyCredit = { viewModel.modifyCredit(index, it) },
                        onDeleteTask = { viewModel.deleteCredit(index, it) }
                    )
                }
            }
        }
    }
}