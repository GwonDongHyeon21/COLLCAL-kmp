package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray2
import org.collcal.collcal.presentation.ui.theme.gray7
import org.collcal.collcal.presentation.ui.theme.red

@Composable
fun SignUpUserInfo(
    modifier: Modifier,
    viewModel: SignViewModel,
    onClick: () -> Unit,
) {
    val checkRedundancy by viewModel.checkRedundancy.collectAsState()

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var signInCheck by remember { mutableStateOf("") }
    var idChanged by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .background(gray2, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Text(text = Strings.id, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(id) {
                id = it
                idChanged = true
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = checkRedundancy.first,
                    modifier = Modifier.padding(start = 30.dp),
                    color = if (checkRedundancy.second) gray7 else red
                )
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {
                        viewModel.checkRedundancy(id)
                        signInCheck = ""
                        idChanged = false
                    },
                    modifier = Modifier.padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = gray7)
                ) {
                    Text(
                        text = Strings.checkRedundancy,
                        color = black,
                        fontWeight = FontWeight.W500
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Text(text = Strings.password, fontWeight = FontWeight.W500)
            CustomPasswordTextField(
                password,
                passwordVisible,
                { password = it },
                { passwordVisible = !passwordVisible }
            )

            Spacer(Modifier.height(10.dp))
            Text(text = Strings.phoneNumber, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(phoneNumber) { phoneNumber = it }

            Spacer(Modifier.height(10.dp))
            Text(text = Strings.email, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(email) { email = it }
        }

        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                signInCheck = when {
                    listOf(id, password, phoneNumber, email).any { it.isBlank() } -> "빈칸을 모두 입력해주세요"
                    idChanged -> "아이디 중복 체크를 해주세요"
                    !checkRedundancy.second -> "다른 아이디를 입력해주세요"
                    else -> {
                        viewModel.saveUserInfo(id, password, phoneNumber, email)
                        onClick()
                        ""
                    }
                }
            },
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = gray1)
        ) {
            Text(text = Strings.next, color = black)
        }

        Spacer(Modifier.height(10.dp))
        Text(text = signInCheck, color = red)
    }
}