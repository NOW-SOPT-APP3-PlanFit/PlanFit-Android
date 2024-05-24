package org.sopt.app3.planfit.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.app3.planfit.domain.model.MainGet

@Serializable
data class ResponseGetMainDto(
    @SerialName("round")
    val round: Int,
    @SerialName("minute")
    val minute: Int,
    @SerialName("condition")
    val condition: String,
)

fun ResponseGetMainDto.toDomain() = MainGet(
    round = this.round,
    minute = this.minute,
    condition = this.condition
)