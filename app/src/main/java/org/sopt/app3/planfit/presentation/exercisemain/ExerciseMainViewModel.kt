package org.sopt.app3.planfit.presentation.exercisemain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.model.SetCount
import org.sopt.app3.planfit.domain.repo.ExerciseMainRepository

class ExerciseMainViewModel(private val exerciseMainRepository: ExerciseMainRepository) : ViewModel() {
    private val _setList = MutableLiveData(listOf(
        SetCount.InProgress(1),
        SetCount.Remaining(2),
        SetCount.Remaining(3),
        SetCount.Remaining(4),
    ))
    val setList : LiveData<List<SetCount>> get() = _setList

    private val _currentIndex = MutableLiveData<Long>(1)
    val currentIndex: LiveData<Long> = _currentIndex


    fun addExerciseSet(id: Long) {
        viewModelScope.launch {
            exerciseMainRepository.addExerciseSet(1)
                .onSuccess {
                    _setList.value = setList.value?.plus(SetCount.Remaining(id.toInt()))

                    Log.e("setList", setList.value.toString())
                }
                .onFailure {
                    Log.e("add set fail", it.message.toString())
                }
        }
    }

    //fun completeSet() {
    //    _setList.value?.set(currentIndex.value!!.toInt(), SetCount.Completed(currentIndex.value!!.toInt()))
    //    _currentIndex.value = currentIndex.value?.plus(1)
    //    _setList.value?.set(currentIndex.value!!.toInt(), SetCount.InProgress(currentIndex.value!!.toInt()))
    // }

    fun modifySetStatus(id: Long) {
        viewModelScope.launch {
            exerciseMainRepository.modifySetStatus(id)
                .onSuccess {
                    _currentIndex.value = id
                }
                .onFailure {
                    Log.e("modify set fail", it.message.toString())
                }
        }
    }
}
