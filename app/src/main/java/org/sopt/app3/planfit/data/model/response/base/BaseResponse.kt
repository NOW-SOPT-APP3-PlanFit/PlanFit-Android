package org.sopt.app3.planfit.data.model.response.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val data: T? = null,
    val message: String,
)
