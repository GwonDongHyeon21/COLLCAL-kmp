package org.collcal.collcal.presentation.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.component.CustomDropDown
import org.collcal.collcal.presentation.component.DownArrowIcon
import org.collcal.collcal.presentation.component.MoreDotsIconButton
import org.collcal.collcal.presentation.component.UpArrowIcon
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray3
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.user.component.CreditAddField
import org.collcal.collcal.presentation.user.model.Credit

@Composable
fun UserItem(
    creditInfo: Triple<String, Triple<Int, Int, MutableState<Boolean>>, List<Credit>>,
    onAddCredit: (Credit) -> Unit,
    onModifyCredit: (Credit) -> Unit,
    onDeleteCredit: (Credit) -> Unit,
) {
    var creditsExpanded by remember { mutableStateOf(creditInfo.second.third.value) }
    var isAdd by remember { mutableStateOf(false) }
    var course by remember { mutableStateOf("") }
    var credit by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(containerColor = gray3)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = creditInfo.first,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W800
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${creditInfo.second.first}/${creditInfo.second.second}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W300
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = if (creditsExpanded) UpArrowIcon else DownArrowIcon,
                    contentDescription = "DownArrowIcon",
                    tint = mainColor,
                    modifier = Modifier.clickable(
                        onClick = {
                            creditsExpanded = !creditsExpanded
                            isAdd = false
                            course = ""
                            credit = ""
                            grade = ""
                        },
                        interactionSource = null,
                        indication = null
                    )
                )
            }

            if (creditsExpanded) {
                creditInfo.third.forEach {
                    var isModify by remember { mutableStateOf(false) }
                    var moreActionExpanded by remember { mutableStateOf(false) }

                    if (isModify) {
                        var nowCourse by remember { mutableStateOf(it.course) }
                        var nowCredit by remember { mutableStateOf(it.credit.toString()) }
                        var nowGrade by remember { mutableStateOf(it.grade) }
                        CreditAddField(
                            course = nowCourse,
                            credit = nowCredit,
                            grade = nowGrade,
                            onCourseChanged = { nowCourse = it },
                            onCreditChanged = { nowCredit = it },
                            onGradeChanged = { nowGrade = it },
                            onClick = { newCourse, newCredit, newGrade ->
                                onModifyCredit(
                                    Credit(
                                        it.creditId,
                                        it.userId,
                                        it.courseCategory,
                                        newCourse,
                                        newCredit.toInt(),
                                        newGrade
                                    )
                                )
                                isModify = !isModify
                            }
                        )
                    } else
                        Card(
                            shape = RoundedCornerShape(7.dp),
                            border = BorderStroke(1.dp, gray4)
                        ) {
                            Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                                Text(
                                    text = it.course,
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                                Text(text = it.credit.toString(), modifier = Modifier.align(Alignment.Center))
                                Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Text(text = it.grade)
                                        MoreDotsIconButton {
                                            moreActionExpanded = !moreActionExpanded
                                        }
                                    }
                                    CustomDropDown(
                                        options = listOf(Strings.modify, Strings.delete),
                                        isExpanded = moreActionExpanded,
                                        onClickExpanded = {
                                            moreActionExpanded = !moreActionExpanded
                                        },
                                        onClickOption = { option ->
                                            when (option) {
                                                Strings.modify -> isModify = !isModify
                                                Strings.delete -> onDeleteCredit(it)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                }

                if (isAdd)
                    CreditAddField(
                        course = course,
                        credit = credit,
                        grade = grade,
                        onCourseChanged = { course = it },
                        onCreditChanged = { credit = it },
                        onGradeChanged = { grade = it },
                        onClick = { newCourse, newCredit, newGrade ->
                            onAddCredit(
                                Credit(
                                    "",
                                    "",
                                    0,
                                    newCourse,
                                    newCredit.toInt(),
                                    newGrade
                                )
                            )
                            isAdd = !isAdd
                            course = ""
                            credit = ""
                            grade = ""
                        }
                    )

                Card(
                    shape = RoundedCornerShape(7.dp),
                    border = BorderStroke(1.dp, gray4)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                onClick = { isAdd = !isAdd },
                                interactionSource = null,
                                indication = null
                            )
                            .border(1.dp, black, RoundedCornerShape(5.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "+ ${Strings.add}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400
                        )
                    }
                }
            }
        }
    }
}