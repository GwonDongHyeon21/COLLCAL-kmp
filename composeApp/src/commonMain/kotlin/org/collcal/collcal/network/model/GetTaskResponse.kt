package org.collcal.collcal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.collcal.collcal.presentation.tasks.model.Task

@Serializable
data class GetTaskResponse(
    @SerialName("message") val message: String,
    @SerialName("tasks") val tasks: List<Task>? = null,
)