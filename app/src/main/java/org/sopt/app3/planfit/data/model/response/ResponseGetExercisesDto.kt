package org.sopt.app3.planfit.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetExercisesDto(
    @SerialName("exercises")
    val exercises: List<Exercise>
){
    @Serializable
    data class Exercise(
        @SerialName("count")
        val count: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("index")
        val index: Int,
        @SerialName("name")
        val name: String,
        @SerialName("set")
        val `set`: Int,
        @SerialName("weight")
        val weight: Int
    )
}
