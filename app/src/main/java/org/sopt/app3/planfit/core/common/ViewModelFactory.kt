package org.sopt.app3.planfit.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.app3.planfit.data.ServicePool
import org.sopt.app3.planfit.data.repo.ExerciseListRepositoryImpl
import org.sopt.app3.planfit.data.repo.ExerciseMainRepositoryImpl
import org.sopt.app3.planfit.presentation.exerciselist.ExerciseViewModel
import org.sopt.app3.planfit.presentation.exercisemain.ExerciseMainViewModel
import org.sopt.app3.planfit.presentation.provider.ResourceProviderImpl

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            return ExerciseViewModel(ExerciseListRepositoryImpl(ServicePool.exerciseListService, ResourceProviderImpl())) as T
        }
        else if(modelClass.isAssignableFrom(ExerciseMainViewModel::class.java)) {
            return ExerciseMainViewModel(ExerciseMainRepositoryImpl(ServicePool.exerciseMainService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}