package org.sopt.app3.planfit.data.api

import org.sopt.app3.planfit.data.model.request.RequestPutMainDto
import org.sopt.app3.planfit.data.model.response.ResponseGetMainDto
import org.sopt.app3.planfit.data.model.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface MainService {
    @PUT("/api/v1/main")
    suspend fun putMain(
        @Body requestPutMainDto: RequestPutMainDto
    ): BaseResponse<Unit>

    @GET("/api/v1/main")
    suspend fun getMain(
    ): BaseResponse<ResponseGetMainDto>
}