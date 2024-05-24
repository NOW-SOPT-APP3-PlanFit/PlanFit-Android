package org.sopt.app3.planfit.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPutExercisesIndexDto(
    @SerialName("exercises")
    val exercises: List<Exercise>
){
    @Serializable
    data class Exercise(
        @SerialName("id")
        val id: Int,
        @SerialName("index")
        val index: Int
    )
}