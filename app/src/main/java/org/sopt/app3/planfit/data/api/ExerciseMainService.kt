package org.sopt.app3.planfit.data.api

import org.sopt.app3.planfit.data.model.response.base.BaseResponse
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExerciseMainService {
    @POST("/api/v1/exercises/{exerciseId}/set")
    suspend fun postExercisesSet(
        @Path("exerciseId") exerciseId:Long
    ): BaseResponse<Unit>

    @PUT("/api/v1/exercises/{exerciseId}/set")
    suspend fun putExercisesSet(
        @Path("exerciseId") exerciseId: Long
    ): BaseResponse<Unit>
}