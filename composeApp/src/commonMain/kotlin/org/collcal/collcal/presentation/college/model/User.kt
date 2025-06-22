package org.collcal.collcal.presentation.college.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("message") val message: String,
    @SerialName("university") val university: String,
    @SerialName("studentId") val studentId: Int,
    @SerialName("major") val department: String,
    @SerialName("status") val semester: String,
    val semesterInt: Int? = 0,
    val majorBasicCredits: Int? = 0,
    val majorRequiredCredits: Int? = 0,
    val majorElectiveCredits: Int? = 0,
    val requiredLiberalArtsCredits: Int? = 0,
    val distributedCredits: Int? = 0,
    val freeComplementCredits: Int? = 0,
)