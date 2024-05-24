package org.sopt.app3.planfit.domain.repo

interface ExerciseMainRepository {
    suspend fun addExerciseSet(indexSet: Long) : Result<Unit>
    suspend fun modifySetStatus(indexSet: Long) : Result<Unit>
}