package org.sopt.app3.planfit.domain.repo

interface LikeRepo {
    suspend fun changeToLike(exerciseId: Long) : Result<Unit>
    suspend fun changeToUnLike(exerciseId: Long) : Result<Unit>
}