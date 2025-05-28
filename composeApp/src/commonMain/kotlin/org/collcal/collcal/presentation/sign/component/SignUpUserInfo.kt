package org.collcal.collcal.presentation.sign.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.collcal.collcal.presentation.sign.SignViewModel
import org.collcal.collcal.presentation.ui.theme.Strings
import org.collcal.collcal.presentation.ui.theme.black
import org.collcal.collcal.presentation.ui.theme.gray1
import org.collcal.collcal.presentation.ui.theme.gray2

@Composable
fun SignUpUserInfo(
    modifier: Modifier,
    viewModel: SignViewModel,
    onClick: () -> Unit,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .background(gray2, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Text(text = Strings.id, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(id) { id = it }

            Spacer(Modifier.height(10.dp))
            Text(text = Strings.password, fontWeight = FontWeight.W500)
            CustomOutlinedTextField(password) { password = it }

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
                if (listOf(id, password, phoneNumber, email).all { it.isNotBlank() }) {
                    viewModel.saveUserInfo(id, password, phoneNumber, email)
                    onClick()
                } else isEmpty = true
            },
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = gray1)
        ) {
            Text(text = "다음", color = black)
        }

        Spacer(Modifier.height(10.dp))
        Text(
            text = if (isEmpty) "빈칸을 모두 입력해주세요" else "",
            color = if (isEmpty) Color.Red else Color.Transparent
        )
    }
}