package org.collcal.collcal.presentation.collegedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.collcal.collcal.presentation.collegedetail.model.Task

class CollegeDetailViewModel : ViewModel() {

    fun getTaskContent(taskId: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // 예시
                val response = taskId
                response?.let { onResult("") }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun saveTaskContent(taskContent: String) {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                println(e)
            }
        }
    }
}