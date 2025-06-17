package org.collcal.collcal.presentation.college

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.collcal.collcal.network.ApiService
import org.collcal.collcal.presentation.college.model.User
import org.collcal.collcal.presentation.tasks.model.AddTaskRequest
import org.collcal.collcal.presentation.tasks.model.ModifyTaskRequest
import org.collcal.collcal.presentation.tasks.model.Task
import org.collcal.collcal.presentation.user.model.Credit
import kotlin.math.floor

class CollegeViewModel(private val apiService: ApiService = ApiService()) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _userInfo =
        MutableStateFlow(User("", "", "", 0, 0, 0, 0, 0, 0, 0))
    val userInfo: StateFlow<User> = _userInfo

    private val _earnedCredit = MutableStateFlow(0)
    val earnedCredit: StateFlow<Int> = _earnedCredit

    private val _averageCredit = MutableStateFlow(0.0)
    val averageCredit: StateFlow<Double> = _averageCredit

    private val _credits = MutableStateFlow(emptyList<List<Credit>>())
    val credits: StateFlow<List<List<Credit>>> = _credits

    private val _colleges =
        MutableStateFlow(emptyList<Pair<String, List<Pair<Pair<String, Int>, List<Task>>>>>())
    val colleges: StateFlow<List<Pair<String, List<Pair<Pair<String, Int>, List<Task>>>>>> =
        _colleges

    private val _todos = MutableStateFlow(emptyList<Task>())
    val todos: StateFlow<List<Task>> = _todos

    init {
        getTasks()
        getUser()
    }

    private fun getTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val tasks = apiService.getTasks().tasks ?: emptyList()
                _todos.value = tasks.filter { it.status == 0 }
                _colleges.value = listOf(
                    // @formatter:off
                    Pair(
                        "1",
                        listOf(
                            Pair("1학년 1학기" to 1, tasks.filter { it.status == 1 }),
                            Pair("1학년 하계 방학" to 2, tasks.filter { it.status == 2 }),
                            Pair("1학년 2학기" to 3, tasks.filter { it.status == 3 }),
                            Pair("1학년 동계 방학" to 4, tasks.filter { it.status == 4 }),
                        )
                    ),
                    Pair(
                        "2",
                        listOf(
                            Pair("2학년 1학기" to 5, tasks.filter { it.status == 5 }),
                            Pair("2학년 하계 방학" to 6, tasks.filter { it.status == 6 }),
                            Pair("2학년 2학기" to 7, tasks.filter { it.status == 7 }),
                            Pair("2학년 동계 방학" to 8, tasks.filter { it.status == 8 }),
                        )
                    ),
                    Pair(
                        "3",
                        listOf(
                            Pair("3학년 1학기" to 9, tasks.filter { it.status == 9 }),
                            Pair("3학년 하계 방학" to 10, tasks.filter { it.status == 10 }),
                            Pair("3학년 2학기" to 11, tasks.filter { it.status == 11 }),
                            Pair("3학년 동계 방학" to 12, tasks.filter { it.status == 12 }),
                        )
                    ),
                    Pair(
                        "4",
                        listOf(
                            Pair("4학년 1학기" to 13, tasks.filter { it.status == 13 }),
                            Pair("4학년 하계 방학" to 14, tasks.filter { it.status == 14 }),
                            Pair("4학년 2학기" to 15, tasks.filter { it.status == 15 }),
                            Pair("4학년 동계 방학" to 16, tasks.filter { it.status == 16 }),
                        )
                    )
                    // @formatter:on
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
                    User(
                        "권동현",
                        "컴퓨터공학과",
                        "2학년 1학기",
                        5,
                        12,
                        12,
                        45,
                        50,
                        39,
                        45,
                        34,
                        50,
                        32,
                        30,
                        6,
                        6
                    )
                _courses.value = listOf(
                    listOf(
                        Credit("0", "실험통계학", "3", "A0"),
                        Credit("1", "미분적분학", "3", "A0"),
                        Credit("2", "일반물리", "3", "A0")
                    ), emptyList(), emptyList(), emptyList(), emptyList(), emptyList()
                )
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun calculateCredit() {
        _earnedCredit.value =
            _courses.value.flatten().sumOf { if (it.grade == "-") 0 else it.credit.toInt() }

        val sumOfCreditMultiGrade = _courses.value.flatten()
            .sumOf { (if (it.credit == "-") 0 else it.credit.toInt()) * getGradeToDouble(it.grade) }
        val sumOfGrade = _courses.value.flatten()
            .sumOf { if (it.credit == "-") 0 else it.credit.toInt() }
        _averageCredit.value = floor(sumOfCreditMultiGrade / sumOfGrade * 1000) / 1000
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
            "-" -> 0.0
            else -> 0.0
        }
    }

    fun changeTaskSemester(task: Task, semesterInt: Int) {
        viewModelScope.launch {
            try {
                _todos.value = _todos.value.filterNot { it.taskId == task.taskId }
                _colleges.value = _colleges.value.map { (key, semesters) ->
                    key to semesters.map { (semesterPair, tasks) ->
                        val hasTask = tasks.any { it.taskId == task.taskId }
                        val isSameSemester = semesterPair.second == semesterInt
                        semesterPair to when {
                            hasTask && isSameSemester -> tasks
                            hasTask -> tasks.filterNot { it.taskId == task.taskId }
                            isSameSemester -> tasks + task
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
                val response = apiService.addTask(AddTaskRequest(title, info, ""))
                if (response.message == "할 일이 성공적으로 추가되었습니다.") {
                    _todos.value += Task("", "", title, info, "", 0)
                    _todos.value =
                        (apiService.getTasks().tasks ?: emptyList()).filter { it.status == 0 }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun modifyTask(task: Task, title: String, info: String) {
        viewModelScope.launch {
            try {
                val response =
                    apiService.modifyTask(ModifyTaskRequest(task.taskId, title, info, ""))
                if (response.message == "성공적으로 업데이트되었습니다.") {
                    if (_todos.value.find { it.taskId == task.taskId } != null)
                        _todos.value = _todos.value.map {
                            if (it.taskId == task.taskId) task.copy(title = title, info = info)
                            else it
                        }
                    else
                        _colleges.value = _colleges.value.map { college ->
                            college.copy(second = college.second.map { semester ->
                                semester.copy(second = semester.second.map {
                                    if (it.taskId == task.taskId) task.copy(
                                        title = title,
                                        info = info
                                    )
                                    else it
                                })
                            })
                        }
                    getTasks()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun moveToTodoTask(task: Task) {
        viewModelScope.launch {
            try {
                deleteTask(task) { _todos.value += task }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteTask(task: Task, onResult: () -> Unit) {
        viewModelScope.launch {
            try {
                if (_todos.value.find { it.taskId == task.taskId } != null)
                    _todos.value = _todos.value.filterNot { it.taskId == task.taskId }
                else
                    _colleges.value = _colleges.value.map { college ->
                        college.copy(second = college.second.map { semester ->
                            semester.copy(second = semester.second.filterNot { it.taskId == task.taskId })
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
                _courses.value = _courses.value
                    .toMutableList()
                    .apply { this[creditList] = this[creditList] + credit }
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun modifyCredit(creditList: Int, credit: Credit) {
        viewModelScope.launch {
            try {
                _courses.value =
                    _courses.value.map { list -> list.map { if (it.id == credit.id) credit else it } }
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteCredit(creditList: Int, credit: Credit) {
        viewModelScope.launch {
            try {
                _courses.value =
                    _courses.value.map { list -> list.filterNot { it.id == credit.id } }
                getCredit()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}