package org.sopt.app3.planfit.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.app3.planfit.data.ServicePool
import org.sopt.app3.planfit.data.repo.ExerciseListRepositoryImpl
import org.sopt.app3.planfit.data.repo.MainRepositoryImpl
import org.sopt.app3.planfit.presentation.exerciseconditionlist.ExerciseConditionListViewModel
import org.sopt.app3.planfit.presentation.exerciselist.ExerciseViewModel
import org.sopt.app3.planfit.presentation.exercisetimelist.ExerciseTimeListViewModel
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
        } else if (modelClass.isAssignableFrom(ExerciseTimeListViewModel::class.java)) {
            return ExerciseTimeListViewModel(MainRepositoryImpl(ServicePool.mainService)) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}