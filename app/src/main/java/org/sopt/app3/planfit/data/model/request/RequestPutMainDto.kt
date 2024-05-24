package org.sopt.app3.planfit.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPutMainDto(
    @SerialName("minute")
    val minute: Int,
    @SerialName("condition")
    val condition: String,
)
