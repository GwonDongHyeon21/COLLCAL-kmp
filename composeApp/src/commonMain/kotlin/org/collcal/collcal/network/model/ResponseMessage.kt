package org.collcal.collcal.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMessage(
    @SerialName("message") val message: String,
)
