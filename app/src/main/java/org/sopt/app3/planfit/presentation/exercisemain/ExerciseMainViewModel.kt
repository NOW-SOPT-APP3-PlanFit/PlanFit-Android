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
    private val _setList = MutableLiveData(
        mutableListOf(
        SetCount.InProgress(1),
        SetCount.Remaining(2),
        SetCount.Remaining(3),
        SetCount.Remaining(4),
    ))
    val setList : LiveData<MutableList<SetCount>> get() = _setList

    private val _currentIndex = MutableLiveData<Long>(1)
    val currentIndex: LiveData<Long> = _currentIndex


    fun addExerciseSet(id: Long) {
        viewModelScope.launch {
            exerciseMainRepository.addExerciseSet(1) // 운동 고유 아이디
                .onSuccess {
                    val tempList = setList.value?.toList()?.plus(SetCount.Remaining(id.toInt()))
                    _setList.value = tempList?.toMutableList()
                }
                .onFailure {
                    Log.e("add set fail", it.message.toString())
                }
        }
    }

    fun modifySetStatus(id: Long) {
        viewModelScope.launch {
            exerciseMainRepository.modifySetStatus(1)
                .onSuccess {
                    val setListValue = _setList.value
                    _currentIndex.value = currentIndex.value?.plus(1)
                    setListValue?.set((id-1).toInt(), SetCount.Completed(id.toInt())) // 기본 인덱스는 0부터 시작
                    setListValue?.set(id.toInt(), SetCount.InProgress((id+1).toInt()))
                }
                .onFailure {
                    Log.e("modify set fail", it.message.toString())
                }
        }
    }
}
