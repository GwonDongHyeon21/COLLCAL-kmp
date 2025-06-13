package org.collcal.collcal.presentation.college

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.collcal.collcal.presentation.college.model.User
import org.collcal.collcal.presentation.collegedetail.model.Task
import org.collcal.collcal.presentation.user.model.Credit
import kotlin.math.floor

class CollegeViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _userInfo = MutableStateFlow(User("", "", "", 0, 0, 0.0, 0, 0, 0, 0, 0, 0, 0, 0))
    val userInfo: StateFlow<User> = _userInfo

    private val _earnedCredit = MutableStateFlow(0)
    val earnedCredit: StateFlow<Int> = _earnedCredit

    private val _averageCredit = MutableStateFlow(0.0)
    val averageCredit: StateFlow<Double> = _averageCredit

    private val _aList = MutableStateFlow(emptyList<Credit>())
    val aList: StateFlow<List<Credit>> = _aList

    private val _bList = MutableStateFlow(emptyList<Credit>())
    val bList: StateFlow<List<Credit>> = _bList

    private val _cList = MutableStateFlow(emptyList<Credit>())
    val cList: StateFlow<List<Credit>> = _cList

    private val _dList = MutableStateFlow(emptyList<Credit>())
    val dList: StateFlow<List<Credit>> = _dList

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
        getCredit()
    }

    private fun getCollegeData() {
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
                                    Task("2", "전공연수", "하계 독일") to false
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
                _aList.value =
                    listOf(
                        Credit("0", "실험통계학", "3", "A0"),
                        Credit("1", "미분적분학", "3", "A0"),
                        Credit("2", "일반물리", "3", "A0")
                    )
                _bList.value = emptyList()
                _cList.value = emptyList()
                _dList.value = emptyList()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun getCredit() {
        _earnedCredit.value = _aList.value.sumOf { it.credit.toInt() } +
                _bList.value.sumOf { it.credit.toInt() } +
                _cList.value.sumOf { it.credit.toInt() } +
                _dList.value.sumOf { it.credit.toInt() }
        val allList = _aList.value + _bList.value + _cList.value + _dList.value
        _averageCredit.value =
            floor(allList.sumOf { it.credit.toInt() * getGradeToDouble(it.grade) } / allList.sumOf { it.credit.toInt() } * 1000) / 1000
    }

    private fun getGradeToDouble(grade: String): Double {
        return when (grade) {
            "A+" -> 4.3
            "A0" -> 4.0
            "A-" -> 3.7
            "B+" -> 3.3
            "B0" -> 3.0
            "B-" -> 2.7
            "C+" -> 2.3
            "C0" -> 2.0
            "C-" -> 1.7
            "D+" -> 1.3
            "D0" -> 1.0
            "D-" -> 0.7
            "F" -> 0.0
            "P" -> 0.0
            "NP" -> 0.0
            else -> 0.0
        }
    }

    private fun getTodos() {
        viewModelScope.launch {
            try {
                _todos.value =
                    listOf(
                        Pair(Task("3", "학부연구생", ""), false),
                        Pair(Task("4", "현장실습", "삼성 SEMES"), false),
                        Pair(Task("5", "인턴", "현대 SEMES"), false)
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
                    Task((_todos.value.size + _colleges.value.size).toString(), title, info), false
                )
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun modifyTask(taskId: String, title: String, info: String) {
        viewModelScope.launch {
            try {
                if (_todos.value.find { it.first.id == taskId } != null)
                    _todos.value = _todos.value.map { (task, completed) ->
                        if (task.id == taskId)
                            task.copy(content = title, info = info) to completed
                        else
                            task to completed
                    }
                else
                    _colleges.value = _colleges.value.map { college ->
                        college.copy(second = college.second.map { semester ->
                            semester.copy(second = semester.second.map { taskPair ->
                                val task = taskPair.first
                                val updatedTaskList =
                                    if (task.id == taskId) task.copy(content = title, info = info)
                                    else task
                                taskPair.copy(first = updatedTaskList)
                            })
                        })
                    }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun moveToTodoTask(task: Task) {
        viewModelScope.launch {
            try {
                deleteTask(task.id) { _todos.value += Pair(task, false) }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteTask(taskId: String, onResult: () -> Unit) {
        viewModelScope.launch {
            try {
                if (_todos.value.find { it.first.id == taskId } != null)
                    _todos.value = _todos.value.filterNot { it.first.id == taskId }
                else
                    _colleges.value = _colleges.value.map { college ->
                        college.copy(second = college.second.map { semester ->
                            semester.copy(second = semester.second.filterNot { it.first.id == taskId })
                        })
                    }
            } catch (e: Exception) {
                println(e)
            }
            onResult()
        }
    }

    fun addCredit(creditList: Int, credit: Credit) {
        viewModelScope.launch {
            try {
                when (creditList) {
                    0 -> _aList.value += credit
                    1 -> _bList.value += credit
                    2 -> _cList.value += credit
                    3 -> _dList.value += credit
                }
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun modifyCredit(creditList: Int, credit: Credit) {
        viewModelScope.launch {
            try {
                when (creditList) {
                    0 -> _aList.value = _aList.value.map { if (it.id == credit.id) credit else it }
                    1 -> _bList.value = _aList.value.map { if (it.id == credit.id) credit else it }
                    2 -> _cList.value = _aList.value.map { if (it.id == credit.id) credit else it }
                    3 -> _dList.value = _aList.value.map { if (it.id == credit.id) credit else it }
                }
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteCredit(creditList: Int, credit: Credit) {
        viewModelScope.launch {
            try {
                when (creditList) {
                    0 -> _aList.value = _aList.value.filter { it.id != credit.id }
                    1 -> _bList.value = _aList.value.filter { it.id != credit.id }
                    2 -> _cList.value = _aList.value.filter { it.id != credit.id }
                    3 -> _dList.value = _aList.value.filter { it.id != credit.id }
                }
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}