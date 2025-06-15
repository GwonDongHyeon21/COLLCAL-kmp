package org.collcal.collcal.presentation.sign.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignIn(
    @SerialName("loginId") val id: String,
    @SerialName("password") val password: String,
)
