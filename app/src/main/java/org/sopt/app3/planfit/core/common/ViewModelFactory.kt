package org.sopt.app3.planfit.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.app3.planfit.PlanFitApp
import org.sopt.app3.planfit.data.ServicePool
import org.sopt.app3.planfit.data.repo.ExerciseListRepositoryImpl
import org.sopt.app3.planfit.data.repo.MainRepositoryImpl
import org.sopt.app3.planfit.presentation.exerciseconditionlist.ExerciseConditionListViewModel
import org.sopt.app3.planfit.data.repo.ExerciseMainRepositoryImpl
import org.sopt.app3.planfit.data.repo.LikeRepoImpl
import org.sopt.app3.planfit.presentation.MainViewModel
import org.sopt.app3.planfit.presentation.StretchingMainViewModel
import org.sopt.app3.planfit.presentation.exerciselist.ExerciseViewModel
import org.sopt.app3.planfit.presentation.exercisemain.LikeViewModel
import org.sopt.app3.planfit.presentation.exercisemain.ExerciseMainViewModel
import org.sopt.app3.planfit.presentation.provider.ResourceProviderImpl

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            return ExerciseViewModel(
                ExerciseListRepositoryImpl(
                    ServicePool.exerciseListService,
                    ResourceProviderImpl()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(ExerciseConditionListViewModel::class.java)) {
            return ExerciseConditionListViewModel(MainRepositoryImpl(ServicePool.mainService)) as T
        } else if (modelClass.isAssignableFrom(ExerciseMainViewModel::class.java)) {
            return ExerciseMainViewModel(ExerciseMainRepositoryImpl(ServicePool.exerciseMainService), PlanFitApp.stopWatch) as T
        } else if (modelClass.isAssignableFrom(LikeViewModel::class.java)) {
            return LikeViewModel(LikeRepoImpl(ServicePool.likeService)) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepositoryImpl(ServicePool.mainService)) as T
        } else if (modelClass.isAssignableFrom(StretchingMainViewModel::class.java)) {
            return StretchingMainViewModel(PlanFitApp.stopWatch) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}