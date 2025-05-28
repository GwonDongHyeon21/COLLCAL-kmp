package org.collcal.collcal.presentation.college

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CollegeViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _colleges =
        MutableStateFlow(emptyList<Pair<String, List<Pair<String, List<String>>>>>())
    val colleges: StateFlow<List<Pair<String, List<Pair<String, List<String>>>>>> = _colleges

    fun getCollegeData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _colleges.value = listOf(
                    Pair(
                        "1",
                        listOf(
                            Pair(
                                "1학기",
                                listOf("1-1-1", "1-1-2", "1-1-3", "1-1-1", "1-1-2", "1-1-3")
                            ),
                            Pair("여름 방학", listOf("1-2-1", "1-2-2", "1-2-3")),
                            Pair("2학기", listOf("1-3-1", "1-3-2", "1-3-3")),
                            Pair("겨울 방학", listOf("1-4-1", "1-4-2", "1-4-3"))
                        )
                    ),
                    Pair(
                        "2",
                        listOf(
                            Pair("1학기", listOf("2-1-1", "2-1-2", "2-1-3")),
                            Pair("여름 방학", listOf("2-2-1", "2-2-2", "2-2-3")),
                            Pair("2학기", listOf("2-3-1", "2-3-2", "2-3-3")),
                            Pair("겨울 방학", listOf("2-4-1", "2-4-2", "2-4-3"))
                        )
                    ),
                    Pair(
                        "3",
                        listOf(
                            Pair("1학기", listOf("3-1-1", "3-1-2", "3-1-3")),
                            Pair("여름 방학", listOf("3-2-1", "3-2-2", "3-2-3")),
                            Pair("2학기", listOf("3-3-1", "3-3-2", "3-3-3")),
                            Pair("겨울 방학", listOf("3-4-1", "3-4-2", "3-4-3"))
                        )
                    ),
                    Pair(
                        "4",
                        listOf(
                            Pair("1학기", listOf("4-1-1", "4-1-2", "4-1-3")),
                            Pair("여름 방학", listOf("4-2-1", "4-2-2", "4-2-3")),
                            Pair("2학기", listOf("4-3-1", "4-3-2", "4-3-3")),
                            Pair("겨울 방학", listOf("4-4-1", "4-4-2", "4-4-3"))
                        )
                    )
                )
            } catch (e: Exception) {
//                Log.d("CollCalException")
            }
            _isLoading.value = false
        }
    }
}