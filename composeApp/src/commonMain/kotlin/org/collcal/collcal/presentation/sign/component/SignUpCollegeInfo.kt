package org.collcal.collcal.presentation.sign.component

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
import androidx.compose.runtime.collectAsState
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
import org.collcal.collcal.presentation.component.CustomDropDown
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.sign.model.Info
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.mainColor
import org.collcal.collcal.presentation.ui.theme.red
import org.collcal.collcal.presentation.ui.theme.transparent
import org.collcal.collcal.presentation.ui.theme.white

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

    val isSignUp by viewModel.isSignUp.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            infoList.forEach { info ->
                Column {
                    Text(
                        text = info.label,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.padding(start = 10.dp, bottom = 2.dp)
                    )
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

            Spacer(Modifier.height(50.dp))
            Button(
                onClick = {
                    if (listOf(year, school, department, semester).all { it.value.isNotBlank() })
                        viewModel.signUp(
                            year.value,
                            school.value,
                            department.value,
                            semester.value
                        ) { navigator.replaceTo(Screen.SignIn) }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = mainColor)
            ) {
                Text(text = Strings.signUp, color = white)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = isSignUp,
                    color = red
                )
            }
        }
    }
}