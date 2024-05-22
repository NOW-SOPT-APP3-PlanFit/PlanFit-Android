package org.sopt.app3.planfit.data.api

import org.sopt.app3.planfit.data.model.request.RequestPutExercisesIndexDto
import org.sopt.app3.planfit.data.model.response.ResponseGetExercisesDto
import org.sopt.app3.planfit.data.model.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ExerciseListService {
    @PUT("/api/v1/exercises")
    suspend fun putExercisesIndex(
        @Body requestPutExercisesIndexDto: RequestPutExercisesIndexDto
    ): BaseResponse<Unit>

    @GET("/api/v1/exercises")
    suspend fun getExercises(
    ): BaseResponse<ResponseGetExercisesDto>
}