package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.collcal.collcal.navigation.Navigator
import org.collcal.collcal.navigation.Screen
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.sign.model.Info
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray2
import org.collcal.collcal.presentation.ui.theme.red
import org.collcal.collcal.presentation.ui.theme.transparent

@Composable
fun SignUpCollegeInfo(
    modifier: Modifier,
    viewModel: SignViewModel,
    navigator: Navigator,
) {
    val years = viewModel.years
    val schools = viewModel.schools
    val departments = viewModel.departments
    val semesters = viewModel.semesters

    val year = remember { mutableStateOf("") }
    val school = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }
    val semester = remember { mutableStateOf("") }

    val yearsExpanded = remember { mutableStateOf(false) }
    val schoolExpended = remember { mutableStateOf(false) }
    val departmentsExpended = remember { mutableStateOf(false) }
    val semesterExpended = remember { mutableStateOf(false) }

    val infoList = listOf(
        Info(Strings.admissionYear, Strings.admissionYearPlaceholder, years, year, yearsExpanded),
        Info(Strings.school, Strings.schoolPlaceholder, schools, school, schoolExpended),
        Info(
            Strings.department,
            Strings.departmentPlaceholder,
            departments,
            department,
            departmentsExpended
        ),
        Info(Strings.semester, Strings.semesterPlaceholder, semesters, semester, semesterExpended)
    )

    var isEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier.background(gray2, RoundedCornerShape(10.dp)).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            infoList.forEach { info ->
                Column {
                    Text(text = info.label, fontWeight = FontWeight.W500)
                    CustomDropDownTextField(
                        text = info.option.value,
                        placeholder = info.placeholder,
                        onClick = { info.isExpanded.value = !info.isExpanded.value }
                    )
                    Row {
                        Spacer(Modifier.width(20.dp))
                        Column {
                            CustomDropDown(
                                options = info.options,
                                isExpanded = info.isExpanded.value,
                                onClickExpanded = {
                                    info.isExpanded.value = !info.isExpanded.value
                                },
                                onClickOption = { info.option.value = it }
                            )
                        }
                    }
                }
            }

        }

        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                if (listOf(year, school, department, semester).all { it.value.isNotBlank() })
                    viewModel.signUp { navigator.replaceTo(Screen.OnBoarding) }
                else isEmpty = true
            },
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = gray1)
        ) {
            Text(text = Strings.signUp, color = black)
        }

        Spacer(Modifier.height(10.dp))
        Text(
            text = if (isEmpty) "빈칸을 모두 선택해주세요" else "",
            color = if (isEmpty) red else transparent
        )
    }
}