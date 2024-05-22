package org.sopt.app3.planfit.data.repo

import org.sopt.app3.planfit.data.api.ExerciseListService
import org.sopt.app3.planfit.data.model.request.RequestPutExercisesIndexDto
import org.sopt.app3.planfit.domain.model.Exercise
import org.sopt.app3.planfit.domain.model.IndexInfo
import org.sopt.app3.planfit.domain.provider.ResourceProvider
import org.sopt.app3.planfit.domain.repo.ExerciseListRepository

class ExerciseListRepositoryImpl(
    private val exerciseListService: ExerciseListService,
    private val resourceProvider: ResourceProvider,
) : ExerciseListRepository {
    override suspend fun getExercises(): Result<List<Exercise>> =
        runCatching {
            exerciseListService.getExercises().data!!.exercises.map {
                Exercise(
                    id = it.id,
                    imageUri = resourceProvider.provideDefaultImg(),
                    count = it.count,
                    title = it.name,
                    index = it.index,
                    set = it.set,
                    weight = it.weight,
                )
            }
        }


    override suspend fun changeExercisesIndex(indexInfos: List<IndexInfo>): Result<Unit> =
        runCatching {
            exerciseListService.putExercisesIndex(RequestPutExercisesIndexDto(indexInfos.map {
                RequestPutExercisesIndexDto.Exercise(
                    id = it.id,
                    index = it.index,
                )
            })).data
        }

}