package org.sopt.app3.planfit.domain.repo

import org.sopt.app3.planfit.domain.model.Exercise
import org.sopt.app3.planfit.domain.model.IndexInfo

interface ExerciseListRepository {
    suspend fun getExercises() : Result<List<Exercise>>
    suspend fun changeExercisesIndex(indexInfos: List<IndexInfo>) : Result<Unit>
}