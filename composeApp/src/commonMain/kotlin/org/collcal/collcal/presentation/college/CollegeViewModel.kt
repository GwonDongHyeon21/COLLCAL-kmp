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
        MutableStateFlow(emptyList<Pair<String, List<Pair<Pair<String, Int>, List<Pair<String, Boolean>>>>>>())
    val colleges: StateFlow<List<Pair<String, List<Pair<Pair<String, Int>, List<Pair<String, Boolean>>>>>>> =
        _colleges

    private val _todos = MutableStateFlow(emptyList<Pair<String, Boolean>>())
    val todos: StateFlow<List<Pair<String, Boolean>>> = _todos

    init {
        getCollegeData()
        getUser()
        getTodos()
    }

    fun getCollegeData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _colleges.value = listOf(
                    Pair(
                        "1",
                        listOf(
                            Pair(
                                "1학년 1학기" to 0,
                                listOf(
                                    "1-1-1" to true,
                                    "1-1-2" to true,
                                    "1-1-3" to true,
                                    "1-1-1" to true,
                                    "1-1-2" to true,
                                    "1-1-3" to true,
                                    "1-1-1" to true,
                                    "1-1-2" to true,
                                    "1-1-3" to true
                                )
                            ),
                            Pair(
                                "1학년 하계 방학" to 1,
                                listOf("1-2-1" to true, "1-2-2" to true, "1-2-3" to true)
                            ),
                            Pair(
                                "1학년 2학기" to 2,
                                listOf("1-3-1" to true, "1-3-2" to false, "1-3-3" to true)
                            ),
                            Pair(
                                "1학년 동계 방학" to 3,
                                listOf("1-4-1" to true, "1-4-2" to false, "1-4-3" to true)
                            )
                        )
                    ),
                    Pair(
                        "2",
                        listOf(
                            Pair(
                                "2학년 1학기" to 4,
                                listOf("2-1-1" to true, "2-1-2" to false, "2-1-3" to false)
                            ),
                            Pair(
                                "2학년 하계 방학" to 5,
                                listOf("2-2-1" to false, "2-2-2" to true, "2-2-3" to true)
                            ),
                            Pair(
                                "2학년 2학기" to 6,
                                listOf("2-3-1" to false, "2-3-2" to false, "2-3-3" to false)
                            ),
                            Pair(
                                "2학년 동계 방학" to 7,
                                listOf("2-4-1" to false, "2-4-2" to false, "2-4-3" to false)
                            )
                        )
                    ),
                    Pair(
                        "3",
                        listOf(
                            Pair(
                                "3학년 1학기" to 8,
                                listOf("3-1-1" to false, "3-1-2" to false, "3-1-3" to false)
                            ),
                            Pair(
                                "3학년 하계 방학" to 9,
                                listOf("3-2-1" to false, "3-2-2" to false, "3-2-3" to false)
                            ),
                            Pair(
                                "3학년 2학기" to 10,
                                listOf("3-3-1" to false, "3-3-2" to false, "3-3-3" to false)
                            ),
                            Pair(
                                "3학년 동계 방학" to 11,
                                listOf("3-4-1" to false, "3-4-2" to false, "3-4-3" to false)
                            )
                        )
                    ),
                    Pair(
                        "4",
                        listOf(
                            Pair(
                                "4학년 1학기" to 12,
                                listOf("4-1-1" to false, "4-1-2" to false, "4-1-3" to false)
                            ),
                            Pair(
                                "4학년 하계 방학" to 13,
                                listOf("4-2-1" to false, "4-2-2" to false, "4-2-3" to false)
                            ),
                            Pair(
                                "4학년 2학기" to 14,
                                listOf("4-3-1" to false, "4-3-2" to false, "4-3-3" to false)
                            ),
                            Pair(
                                "4학년 동계 방학" to 15,
                                listOf("4-4-1" to false, "4-4-2" to false, "4-4-3" to false)
                            )
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

    private fun getTodos() {
        viewModelScope.launch {
            try {
                _todos.value =
                    listOf("ex) 1" to false, "ex) 2" to false, "ex) 3" to false, "ex) 4" to false)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun changeTaskSemester(task: Pair<String, Boolean>, semesterInt: Int) {
        viewModelScope.launch {
            try {
                val newTask = Pair(task.first, semesterInt < _userInfo.value.semesterInt)
                _todos.value = _todos.value.filterNot { it.first == newTask.first }
                _colleges.value = _colleges.value.map { (key, semesters) ->
                    key to semesters.map { (semesterPair, tasks) ->
                        val hasTask = tasks.any { it.first == newTask.first }
                        val isSameSemester = semesterPair.second == semesterInt
                        semesterPair to when {
                            hasTask && isSameSemester -> tasks
                            hasTask -> tasks.filterNot { it.first == newTask.first }
                            isSameSemester -> tasks + newTask
                            else -> tasks
                        }
                    }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}