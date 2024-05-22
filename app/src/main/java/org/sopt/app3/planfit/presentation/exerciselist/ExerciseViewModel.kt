package org.sopt.app3.planfit.presentation.exerciselist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.model.Exercise
import org.sopt.app3.planfit.domain.model.IndexInfo
import org.sopt.app3.planfit.domain.repo.ExerciseListRepository
import retrofit2.HttpException

class ExerciseViewModel(
    private val exerciseListRepository: ExerciseListRepository,
) : ViewModel() {
    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> get() = _exercises

    private val _changeResult = MutableLiveData<Boolean>()
    val changeResult: LiveData<Boolean> get() = _changeResult
    fun getExercises() = viewModelScope.launch {
        exerciseListRepository.getExercises()
            .onSuccess {
                _exercises.value = it
            }
            .onFailure {
                Log.e("getExercises fail", it.message.toString())
            }
    }

    fun changeExercisesIndex(list: List<IndexInfo>) = viewModelScope.launch {
        exerciseListRepository.changeExercisesIndex(list)
            .onSuccess {
                _changeResult.value = true
            }
            .onFailure {
                Log.e("changeExercisesIndex fail", it.message.toString())
            }
    }
}