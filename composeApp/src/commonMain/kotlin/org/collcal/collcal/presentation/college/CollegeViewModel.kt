package org.collcal.collcal.presentation.college

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.collcal.collcal.presentation.college.model.User

class CollegeViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _userInfo = MutableStateFlow(User("", "", "", 0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0))
    val userInfo: StateFlow<User> = _userInfo

    private val _colleges =
        MutableStateFlow(emptyList<Pair<String, List<Triple<String, Int, List<String>>>>>())
    val colleges: StateFlow<List<Pair<String, List<Triple<String, Int, List<String>>>>>> = _colleges

    init {
        getCollegeData()
        getUser()
    }

    fun getCollegeData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _colleges.value = listOf(
                    Pair(
                        "1",
                        listOf(
                            Triple(
                                "1학년 1학기", 0,
                                listOf(
                                    "1-1-1",
                                    "1-1-2",
                                    "1-1-3",
                                    "1-1-1",
                                    "1-1-2",
                                    "1-1-3",
                                    "1-1-1",
                                    "1-1-2",
                                    "1-1-3"
                                )
                            ),
                            Triple("1학년 하계 방학", 1, listOf("1-2-1", "1-2-2", "1-2-3")),
                            Triple("1학년 2학기", 2, listOf("1-3-1", "1-3-2", "1-3-3")),
                            Triple("1학년 동계 방학", 3, listOf("1-4-1", "1-4-2", "1-4-3"))
                        )
                    ),
                    Pair(
                        "2",
                        listOf(
                            Triple("2학년 1학기", 4, listOf("2-1-1", "2-1-2", "2-1-3")),
                            Triple("2학년 하계 방학", 5, listOf("2-2-1", "2-2-2", "2-2-3")),
                            Triple("2학년 2학기", 6, listOf("2-3-1", "2-3-2", "2-3-3")),
                            Triple("2학년 동계 방학", 7, listOf("2-4-1", "2-4-2", "2-4-3"))
                        )
                    ),
                    Pair(
                        "3",
                        listOf(
                            Triple("3학년 1학기", 8, listOf("3-1-1", "3-1-2", "3-1-3")),
                            Triple("3학년 하계 방학", 9, listOf("3-2-1", "3-2-2", "3-2-3")),
                            Triple("3학년 2학기", 10, listOf("3-3-1", "3-3-2", "3-3-3")),
                            Triple("3학년 동계 방학", 11, listOf("3-4-1", "3-4-2", "3-4-3"))
                        )
                    ),
                    Pair(
                        "4",
                        listOf(
                            Triple("4학년 1학기", 12, listOf("4-1-1", "4-1-2", "4-1-3")),
                            Triple("4학년 하계 방학", 13, listOf("4-2-1", "4-2-2", "4-2-3")),
                            Triple("4학년 2학기", 14, listOf("4-3-1", "4-3-2", "4-3-3")),
                            Triple("4학년 동계 방학", 15, listOf("4-4-1", "4-4-2", "4-4-3"))
                        )
                    )
                )
            } catch (e: Exception) {
                println(e)
            }
            _isLoading.value = false
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                _userInfo.value =
                    User("권동현", "컴퓨터공학과", "2학년 1학기", 4, 89, 3.4, 45, 50, 39, 45, 34, 50, 32, 30)
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}