package org.collcal.collcal.presentation.user.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.collcal.collcal.presentation.component.CustomDropDown
import org.collcal.collcal.presentation.component.VCheckIcon
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray4
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.white

@Composable
fun CreditAddField(
    course: String,
    credit: String,
    grade: String,
    onCourseChanged: (String) -> Unit,
    onCreditChanged: (String) -> Unit,
    onGradeChanged: (String) -> Unit,
    onClick: (String, String, String) -> Unit,
) {
    var gradeExpanded by remember { mutableStateOf(false) }
    val grades = listOf(
        "A+",
        "A0",
        "A-",
        "B+",
        "B0",
        "B-",
        "C+",
        "C0",
        "C-",
        "D+",
        "D0",
        "D-",
        "F",
        "P",
        "NP",
        "-"
    )

    Card(
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(1.dp, gray4)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            BasicTextField(
                value = course,
                onValueChange = { onCourseChanged(it) },
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .border(0.5.dp, black, RoundedCornerShape(2.dp))
                    .background(white),
                decorationBox = {
                    Box(
                        modifier = Modifier.padding(5.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (course.isEmpty())
                            Text(
                                text = Strings.course,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                color = gray1
                            )
                        it()
                    }
                },
                maxLines = 1
            )

            BasicTextField(
                value = credit,
                onValueChange = { if (it.all { value -> value.isDigit() }) onCreditChanged(it) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .border(0.5.dp, black, RoundedCornerShape(2.dp))
                    .background(white),
                decorationBox = {
                    Box(
                        modifier = Modifier.padding(5.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (credit.isEmpty())
                            Text(
                                text = Strings.credit,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                color = gray1
                            )
                        it()
                    }
                },
                maxLines = 1
            )

            Column(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = grade,
                    onValueChange = { onGradeChanged(it) },
                    textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .clickable { gradeExpanded = !gradeExpanded }
                        .border(0.5.dp, black, RoundedCornerShape(2.dp))
                        .background(white),
                    decorationBox = {
                        Box(
                            modifier = Modifier.padding(5.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (grade.isEmpty())
                                Text(
                                    text = Strings.grade,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W500,
                                    color = gray1
                                )
                            it()
                        }
                    },
                    enabled = false
                )
                CustomDropDown(
                    options = grades,
                    isExpanded = gradeExpanded,
                    onClickExpanded = { gradeExpanded = !gradeExpanded },
                    onClickOption = { onGradeChanged(it) }
                )
            }

            Icon(
                imageVector = VCheckIcon,
                contentDescription = "VCheckIcon",
                tint = white,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            if (listOf(course, credit, grade).all { it.isNotBlank() })
                                onClick(course, credit, grade)
                        },
                        interactionSource = null,
                        indication = null
                    )
                    .background(mainColor, RoundedCornerShape(5.dp))
                    .padding(2.dp)
            )
        }
    }
}