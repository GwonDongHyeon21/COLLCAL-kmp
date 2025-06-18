package org.collcal.collcal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddCourseRequest(
    @SerialName("category") val courseCategory: Int,
    @SerialName("subject") val course: String,
    @SerialName("credit") val credit: Int,
    @SerialName("gpa") val grade: String,
)