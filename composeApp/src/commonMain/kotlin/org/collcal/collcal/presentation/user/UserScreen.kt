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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import org.collcal.collcal.presentation.college.CollegeViewModel
import org.collcal.collcal.presentation.sign.component.DownArrowIcon
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.gray8
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun UserScreen(
    navigator: Navigator,
    innerPadding: PaddingValues = PaddingValues(0.dp),
) {
    val userInfo by CollegeViewModel().userInfo.collectAsState()

    val modifier = when (getPlatformType()) {
        PlatformType.WEB -> Modifier.fillMaxWidth()
        PlatformType.ANDROID -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
        PlatformType.IOS -> Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(containerColor = gray8)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
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
                        Strings.earnedCredits to userInfo.earnedCredits.toString(),
                        Strings.averageCredit to userInfo.averageCredit.toString()
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
                                text = it.second,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W300
                            )
                        }
                    }
                }
            }

            listOf(
                "전공필수" to (userInfo.userMajorRequiredCredits to userInfo.majorRequiredCredits),
                "전공선택" to (userInfo.userMajorElectiveCredits to userInfo.majorElectiveCredits),
                "필수교양" to (userInfo.userRequiredLiberalArtsCredits to userInfo.requiredLiberalArtsCredits),
                "배분이수" to (userInfo.userDistributedCredits to userInfo.distributedCredits)
            ).forEach {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(containerColor = white)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.first,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W800
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "${it.second.first}/${it.second.second}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W300
                        )
                        Spacer(Modifier.weight(1f))
                        Icon(imageVector = DownArrowIcon, contentDescription = "DownArrowIcon")
                    }
                }
            }
        }
    }
}