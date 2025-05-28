package org.collcal.collcal.presentation.college

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import org.collcal.collcal.presentation.ui.theme.gray3
import org.collcal.collcal.presentation.ui.theme.gray4

@Composable
fun CollegeItem(
    modifier: Modifier,
    collegeItem: List<Pair<String, List<String>>>,
) {
    collegeItem.forEach { college ->
        Card(
            modifier = modifier.fillMaxSize(),
            shape = RoundedCornerShape(7.dp),
            colors = CardDefaults.cardColors(containerColor = gray3)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = college.first,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )

                Spacer(Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.padding(10.dp).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    college.second.forEach {
                        Card(
                            modifier = Modifier
                                .border(0.5.dp, gray4, RoundedCornerShape(9.38.dp))
                                .padding(5.dp),
                            shape = RoundedCornerShape(9.38.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            )
                        ) {
                            Text(text = it, fontSize = 18.75.sp)
                        }
                    }
                }
            }
        }
    }
}