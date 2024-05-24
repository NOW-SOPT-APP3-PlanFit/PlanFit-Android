package org.sopt.app3.planfit.presentation.exerciseconditionlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.model.ExerciseCondition
import org.sopt.app3.planfit.domain.repo.MainRepository


class ExerciseConditionListViewModel(private var mainRepository: MainRepository) : ViewModel() {
    private val _exerciseConditions = MutableLiveData<List<ExerciseCondition>>()
    val exerciseConditions: LiveData<List<ExerciseCondition>> get() = _exerciseConditions

    init {
        _exerciseConditions.value = mutableListOf(
            ExerciseCondition("125%", "컨디션이 평소보다 훨씬 좋습니다."),
            ExerciseCondition("100%", "평소와 같은 상태입니다."),
            ExerciseCondition("75%", "몸이 무겁게 느껴집니다."),
            ExerciseCondition("50%", "피곤하고 기운이 없습니다."),
            ExerciseCondition("25%", "몸 상태가 매우 좋지 않습니다.")
        )
    }

    fun getMain() = viewModelScope.launch {
        mainRepository.getMain()
            .onSuccess {
            }

            .onFailure {
                Log.e("getCondition fail", it.message.toString())

            }
    }
}
