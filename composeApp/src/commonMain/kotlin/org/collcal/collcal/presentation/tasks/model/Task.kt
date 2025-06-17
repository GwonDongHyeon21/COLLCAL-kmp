package org.collcal.collcal.presentation.tasks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("_id") val taskId: String,
    @SerialName("userId") var userId: String,
    @SerialName("taskCategory") var title: String,
    @SerialName("title") val info: String,
    @SerialName("note") val content: String,
    @SerialName("status") val status: Int,
)
