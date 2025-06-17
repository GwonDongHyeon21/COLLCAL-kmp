package org.collcal.collcal.presentation.sign.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUp(
    @SerialName("loginId") val id: String,
    @SerialName("password") val password: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phoneNumber: String,
    @SerialName("university") val university: String,
    @SerialName("studentId") val studentId: Int,
    @SerialName("major") val department: String,
    @SerialName("status") val semester: String,
)