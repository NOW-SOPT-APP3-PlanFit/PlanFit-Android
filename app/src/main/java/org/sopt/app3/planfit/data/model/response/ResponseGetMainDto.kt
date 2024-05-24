package org.sopt.app3.planfit.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMainDto (
    @SerialName("count")
    val round: Int,
    @SerialName("minute")
    val minute: Int,
    @SerialName("condition")
    val condition: String
)