package org.collcal.collcal.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.collcal.collcal.network.ApiService
import org.collcal.collcal.presentation.sign.model.SignIn
import org.collcal.collcal.presentation.sign.model.SignUp

class SignViewModel(private val apiService: ApiService = ApiService()) : ViewModel() {
    val years = (2000..2025).reversed().map { it.toString() }
    val schools = listOf("경희대학교")
    val departments =
        listOf("기계공학과", "화학공학과", "산업공학과", "신소재공학과", "건축학과", "사회기반시스템공학과", "원자력공학과", "환경학및환경공학과")
    val semesters = listOf(
        "1학년 1학기",
        "1학년 하계 방학",
        "1학년 2학기",
        "1학년 동계 방학",
        "2학년 1학기",
        "2학년 하계 방학",
        "2학년 2학기",
        "2학년 동계 방학",
        "3학년 1학기",
        "3학년 하계 방학",
        "3학년 2학기",
        "3학년 동계 방학",
        "4학년 1학기",
        "4학년 하계 방학",
        "4학년 2학기",
        "4학년 동계 방학",
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

    fun signUp(
        year: String,
        school: String,
        department: String,
        semester: String,
        onSignUp: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val response = apiService.signUp(
                    SignUp(
                        _idText.value,
                        _passwordText.value,
                        _emailText.value,
                        _phoneNumberText.value,
                        school,
                        year.takeLast(2).toInt(),
                        department,
                        semester,
                    )
                )
                if (response.message == "회원가입 성공") onSignUp()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun signIn(id: String, password: String, onSignIn: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.signIn(SignIn(id, password))
                if (response.message == "로그인 성공") onSignIn()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}