package org.sopt.app3.planfit.data.api

import org.sopt.app3.planfit.data.model.response.base.BaseResponse
import retrofit2.http.PATCH
import retrofit2.http.Path

interface LikeService {
    @PATCH("/api/v1/exercises/{exerciseId}/like")
    suspend fun patchLike(
        @Path("exerciseId") exerciseId: Long
    ) : BaseResponse<Unit>

    @PATCH("/api/v1/exercises/{exerciseId}/unlike")
    suspend fun patchUnlike(
        @Path("exerciseId") exerciseId: Long
    ) : BaseResponse<Unit>
}