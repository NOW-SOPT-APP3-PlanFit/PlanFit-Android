package org.sopt.app3.planfit.data.repo

import org.sopt.app3.planfit.data.api.ExerciseMainService
import org.sopt.app3.planfit.domain.repo.ExerciseMainRepository

class ExerciseMainRepositoryImpl(private val exerciseMainService: ExerciseMainService):ExerciseMainRepository {
    override suspend fun addExerciseSet(indexSet: Long): Result<Unit> =
        runCatching {
            exerciseMainService.postExercisesSet(indexSet)
        }


    override suspend fun modifySetStatus(indexSet: Long): Result<Unit> =
        runCatching {
            exerciseMainService.putExercisesSet(indexSet)
        }
}