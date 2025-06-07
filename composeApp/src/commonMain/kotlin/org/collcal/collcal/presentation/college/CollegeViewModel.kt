package org.collcal.collcal.presentation.college

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.collcal.collcal.presentation.college.model.User
import org.collcal.collcal.presentation.collegedetail.model.Task

class CollegeViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _userInfo = MutableStateFlow(User("", "", "", 0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0))
    val userInfo: StateFlow<User> = _userInfo

    private val _colleges =
        MutableStateFlow(emptyList<Pair<String, List<Pair<Pair<String, Int>, List<Pair<Task, Boolean>>>>>>())
    val colleges: StateFlow<List<Pair<String, List<Pair<Pair<String, Int>, List<Pair<Task, Boolean>>>>>>> =
        _colleges

    private val _todos =
        MutableStateFlow(emptyList<Pair<Task, Boolean>>())
    val todos: StateFlow<List<Pair<Task, Boolean>>> = _todos

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
                                    Task("0", "경진대회", "데이터톤") to true,
                                    Task("1", "한국어 도우미", "") to true
                                )
                            ),
                            Pair("1학년 하계 방학" to 1, emptyList()),
                            Pair("1학년 2학기" to 2, emptyList()),
                            Pair("1학년 동계 방학" to 3, emptyList())
                        )
                    ),
                    Pair(
                        "2",
                        listOf(
                            Pair("2학년 1학기" to 4, emptyList()),
                            Pair("2학년 하계 방학" to 5, emptyList()),
                            Pair("2학년 2학기" to 6, emptyList()),
                            Pair("2학년 동계 방학" to 7, emptyList())
                        )
                    ),
                    Pair(
                        "3",
                        listOf(
                            Pair("3학년 1학기" to 8, emptyList()),
                            Pair(
                                "3학년 하계 방학" to 9,
                                listOf(
                                    Task("3", "전공연수", "하계 독일") to false
                                )
                            ),
                            Pair("3학년 2학기" to 10, emptyList()),
                            Pair("3학년 동계 방학" to 11, emptyList())
                        )
                    ),
                    Pair(
                        "4",
                        listOf(
                            Pair("4학년 1학기" to 12, emptyList()),
                            Pair("4학년 하계 방학" to 13, emptyList()),
                            Pair("4학년 2학기" to 14, emptyList()),
                            Pair("4학년 동계 방학" to 15, emptyList())
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
                    listOf(
                        Pair(Task("0", "학부연구생", ""), false),
                        Pair(Task("1", "현장실습", "삼성 SEMES"), false),
                        Pair(Task("2", "인턴", "현대 SEMES"), false)
                    )
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun changeTaskSemester(task: Task, semesterInt: Int) {
        viewModelScope.launch {
            try {
                val newTask = Pair(task, semesterInt < _userInfo.value.semesterInt)
                _todos.value = _todos.value.filterNot { it.first.content == newTask.first.content }
                _colleges.value = _colleges.value.map { (key, semesters) ->
                    key to semesters.map { (semesterPair, tasks) ->
                        val hasTask = tasks.any { it.first.content == newTask.first.content }
                        val isSameSemester = semesterPair.second == semesterInt
                        semesterPair to when {
                            hasTask && isSameSemester -> tasks
                            hasTask -> tasks.filterNot { it.first.content == newTask.first.content }
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

    fun addTask(title: String, info: String) {
        viewModelScope.launch {
            try {
                val response = true
                if (response) _todos.value += Pair(
                    Task(_todos.value.size.toString(), title, info), false
                )
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}