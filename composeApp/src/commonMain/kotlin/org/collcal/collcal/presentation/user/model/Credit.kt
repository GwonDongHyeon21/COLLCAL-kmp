package org.collcal.collcal.presentation.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    @SerialName("_id") val creditId: String,
    @SerialName("userId") val userId: String,
    @SerialName("category") val courseCategory: Int,
    @SerialName("subject") val course: String,
    @SerialName("credit") val credit: Int,
    @SerialName("gpa") val grade: String,
)