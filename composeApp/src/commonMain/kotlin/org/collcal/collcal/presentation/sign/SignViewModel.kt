package org.collcal.collcal.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignViewModel : ViewModel() {
    val years = (2000..2025).reversed().map { it.toString() }
    val schools = listOf("경희대학교")
    val departments =
        listOf("기계공학과", "화학공학과", "산업공학과", "신소재공학과", "건축학과", "사회기반시스템공학과", "원자력공학과", "환경학및환경공학과")
    val semesters = listOf(
        "1-1",
        "1학년 하계방학",
        "1-2",
        "1학년 하계방학",
        "2-1",
        "2학년 하계방학",
        "2-2",
        "2학년 하계방학",
        "3-1",
        "3학년 하계방학",
        "3-2",
        "3학년 하계방학",
        "4-1",
        "4학년 하계방학",
        "4-2",
        "4학년 하계방학",
    )

    private val idText = MutableStateFlow("")
    private val passwordText = MutableStateFlow("")
    private val phoneNumberText = MutableStateFlow("")
    private val emailText = MutableStateFlow("")

    fun saveUserInfo(
        id: String,
        password: String,
        phoneNumber: String,
        email: String,
    ) {
        idText.value = id
        passwordText.value = password
        phoneNumberText.value = phoneNumber
        emailText.value = email
    }

    fun signUp(onSignUp: () -> Unit) {
        viewModelScope.launch {
            try {
                // 회원가입
                onSignUp()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}