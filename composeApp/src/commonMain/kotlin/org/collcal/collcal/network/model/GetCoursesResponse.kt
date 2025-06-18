package org.collcal.collcal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.collcal.collcal.presentation.user.model.Credit

@Serializable
data class GetCoursesResponse(
    @SerialName("message") val message: String,
    @SerialName("subjects") val courses: List<Credit>? = null
)