package org.collcal.collcal.presentation.tasks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModifyTaskRequest(
    @SerialName("taskId") val id: String,
    @SerialName("taskCategory") val title: String,
    @SerialName("title") val info: String,
    @SerialName("note") val content: String,
)