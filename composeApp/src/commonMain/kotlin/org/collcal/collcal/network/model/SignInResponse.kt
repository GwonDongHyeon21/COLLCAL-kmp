package org.collcal.collcal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("message") val message: String,
    @SerialName("token") val token: String? = null,
)
