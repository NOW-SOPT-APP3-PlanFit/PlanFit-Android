package org.sopt.app3.planfit.data.repo

import org.sopt.app3.planfit.data.api.LikeService
import org.sopt.app3.planfit.domain.repo.LikeRepo

class LikeRepoImpl(private val likeService: LikeService) : LikeRepo{
    override suspend fun changeToLike(exerciseId: Long): Result<Unit> =
        runCatching {
            likeService.patchLike(exerciseId)
        }

    override suspend fun changeToUnLike(exerciseId: Long): Result<Unit> =
        runCatching {
            likeService.patchUnlike(exerciseId)
        }
}