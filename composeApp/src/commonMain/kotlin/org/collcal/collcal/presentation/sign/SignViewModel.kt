package org.collcal.collcal.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _idText = MutableStateFlow("")
    private val _passwordText = MutableStateFlow("")
    private val _phoneNumberText = MutableStateFlow("")
    private val _emailText = MutableStateFlow("")

    private val _checkRedundancy = MutableStateFlow(Pair("", false))
    val checkRedundancy: StateFlow<Pair<String, Boolean>> = _checkRedundancy

    fun checkRedundancy(id: String) {
        viewModelScope.launch {
            try {
                // 아이디 중복 확인
                val response = id != "asdf"
                if (response) _checkRedundancy.value = Pair("사용 가능합니다", true)
                else _checkRedundancy.value = Pair("사용할 수 없습니다", false)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun saveUserInfo(
        id: String,
        password: String,
        phoneNumber: String,
        email: String,
    ) {
        _idText.value = id
        _passwordText.value = password
        _phoneNumberText.value = phoneNumber
        _emailText.value = email
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