package org.sopt.app3.planfit.data.model.response

import android.provider.ContactsContract.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMainDto(
    @SerialName("data")
    val data: List<Data>,
) {
    @Serializable
    data class Data(
        @SerialName("round")
        val round: Int,
        @SerialName("minute")
        val minute: Int,
        @SerialName("condition")
        val condition: String,
    )
}