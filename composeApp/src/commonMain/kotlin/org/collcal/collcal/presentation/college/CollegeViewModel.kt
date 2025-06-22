package org.collcal.collcal.presentation.college

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.collcal.collcal.network.ApiService
import org.collcal.collcal.network.model.AddCourseRequest
import org.collcal.collcal.network.model.ModifyCourseRequest
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
        MutableStateFlow(User("", "", 0, "", "", 0, 0, 0, 0, 0, 0, 0))
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
        getTasks {}
        getUser {}
        getCredits {}
    }

    fun loadingState(boolean: Boolean) {
        _isLoading.value = boolean
    }

    fun getTasks(onResult: () -> Unit) {
        viewModelScope.launch {
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
                onResult()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun getUser(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getUser()
                if (response.message == "유저 정보 불러오기 성공.") {
                    _userInfo.value = getUserCredit(response)
                    calculateCredit()
                    onResult(true)
                } else onResult(false)
            } catch (e: Exception) {
                println(e)
                onResult(false)
            }
        }
    }

    fun getCredits(onResult: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getCredits().courses ?: emptyList()
                _credits.value = listOf(
                    response.filter { it.courseCategory == 0 },
                    response.filter { it.courseCategory == 1 },
                    response.filter { it.courseCategory == 2 },
                    response.filter { it.courseCategory == 3 },
                    response.filter { it.courseCategory == 4 },
                    response.filter { it.courseCategory == 5 })
                calculateCredit()
                onResult()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun calculateCredit() {
        _earnedCredit.value =
            _credits.value.flatten().sumOf { if (it.grade == "-") 0 else it.credit }

        val sumOfCreditMultiGrade = _credits.value.flatten()
            .sumOf { it.credit * getGradeToDouble(it.grade) }
        val sumOfGrade = _credits.value.flatten()
            .sumOf { if (it.grade == "-") 0 else it.credit }
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
                if (task.status != semesterInt) {
                    val response =
                        apiService.modifyTask(
                            ModifyTaskRequest(
                                task.taskId,
                                task.title,
                                task.info,
                                task.content,
                                semesterInt
                            )
                        )
                    if (response.message == "성공적으로 업데이트되었습니다.") {
                        _todos.value = _todos.value.filterNot { it.taskId == task.taskId }
                        _colleges.value = _colleges.value.map { (key, semesters) ->
                            key to semesters.map { (semesterPair, tasks) ->
                                semesterPair to when {
                                    tasks.any { it.taskId == task.taskId } -> tasks.filterNot { it.taskId == task.taskId }
                                    semesterPair.second == semesterInt -> tasks + task
                                    else -> tasks
                                }
                            }
                        }
                        getTasks {}
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

    fun modifyTask(task: Task, title: String, info: String, content: String) {
        viewModelScope.launch {
            try {
                val response =
                    apiService.modifyTask(
                        ModifyTaskRequest(
                            task.taskId,
                            title,
                            info,
                            content,
                            task.status
                        )
                    )
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
                    getTasks {}
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteTask(task: Task, onResult: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteTask(task)
                if (response.message == "task 삭제 완료") {
                    if (_todos.value.find { it.taskId == task.taskId } != null)
                        _todos.value = _todos.value.filterNot { it.taskId == task.taskId }
                    else
                        _colleges.value = _colleges.value.map { college ->
                            college.copy(second = college.second.map { semester ->
                                semester.copy(second = semester.second.filterNot { it.taskId == task.taskId })
                            })
                        }
                    getTasks {}
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
                val response = apiService.addCredit(
                    AddCourseRequest(
                        creditList,
                        credit.course,
                        credit.credit,
                        credit.grade
                    )
                )
                if (response.message == "과목이 정상적으로 추가되었습니다.") {
                    _credits.value = _credits.value
                        .toMutableList()
                        .apply { this[creditList] = this[creditList] + credit }
                    getCredits {}
                    calculateCredit()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun modifyCredit(creditList: Int, credit: Credit) {
        viewModelScope.launch {
            try {
                val response = apiService.modifyCredit(
                    ModifyCourseRequest(
                        credit.creditId,
                        creditList,
                        credit.course,
                        credit.credit,
                        credit.grade
                    )
                )
                if (response.message == "성공적으로 업데이트되었습니다.") {
                    _credits.value =
                        _credits.value.map { list -> list.map { if (it.creditId == credit.creditId) credit else it } }
                    getCredits {}
                    calculateCredit()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun deleteCredit(credit: Credit) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteCredit(credit)
                if (response.message == "과목 삭제 완료") {
                    _credits.value =
                        _credits.value.map { list -> list.filterNot { it.creditId == credit.creditId } }
                    getCredits {}
                    calculateCredit()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}

fun getUserCredit(info: User): User {
    if (info.department == "산업경영공학과") {
        val semesterInt = when (info.semester) {
            "1학년 1학기" -> 1
            "1학년 하계 방학" -> 2
            "1학년 2학기" -> 3
            "학년 동계 방학" -> 4
            "2학년 1학기" -> 5
            "2학년 하계 방학" -> 6
            "2학년 2학기" -> 7
            "2학년 동계 방학" -> 8
            "3학년 1학기" -> 9
            "3학년 하계 방학" -> 10
            "3학년 2학기" -> 11
            "3학년 동계 방학" -> 12
            "4학년 1학기" -> 13
            "4학년 하계 방학" -> 14
            "4학년 2학기" -> 15
            "4학년 동계 방학" -> 16
            else -> 0
        }

        // @formatter:off
        return when (info.studentId) {
        22 -> User("", info.university, info.studentId, info.department, info.semester, semesterInt,21,6,57,17,12,3)
        23 -> User("", info.university, info.studentId, info.department, info.semester, semesterInt,21,6,57,17,12,3)
        24 -> User("", info.university, info.studentId, info.department, info.semester, semesterInt,24,6,49,17,9,3)
        25 -> User("", info.university, info.studentId, info.department, info.semester, semesterInt,24,6,49,17,9,3)
        else -> User("", info.university, info.studentId, info.department, info.semester, 0,0,0,0,0,0,0)
        }
    } else return User("", info.university, info.studentId, info.department, info.semester, 0,0,0,0,0,0,0)
        // @formatter:on
}