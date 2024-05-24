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
        return when (modelClass) {
            ExerciseViewModel::class.java -> {
                ExerciseViewModel(
                    ExerciseListRepositoryImpl(
                        ServicePool.exerciseListService,
                        ResourceProviderImpl()
                    )
                ) as T
            }
            ExerciseConditionListViewModel::class.java -> {
                ExerciseConditionListViewModel(MainRepositoryImpl(ServicePool.mainService)) as T
            }
            ExerciseMainViewModel::class.java -> {
                ExerciseMainViewModel(ExerciseMainRepositoryImpl(ServicePool.exerciseMainService), PlanFitApp.stopWatch) as T
            }
            LikeViewModel::class.java -> {
                LikeViewModel(LikeRepoImpl(ServicePool.likeService)) as T
            }
            MainViewModel::class.java -> {
                MainViewModel(MainRepositoryImpl(ServicePool.mainService)) as T
            }
            StretchingMainViewModel::class.java -> {
                StretchingMainViewModel(PlanFitApp.stopWatch) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}