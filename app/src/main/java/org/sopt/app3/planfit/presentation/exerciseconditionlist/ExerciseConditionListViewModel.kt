package org.sopt.app3.planfit.presentation.exerciseconditionlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.app3.planfit.domain.model.ExerciseCondition


class ExerciseConditionListViewModel : ViewModel() {
    private val _exerciseConditions = MutableLiveData<List<ExerciseCondition>>()
    val exerciseConditions: LiveData<List<ExerciseCondition>> get() = _exerciseConditions
    init {
        loadMockData()
    }

    private fun loadMockData() {
        val mockData: List<ExerciseCondition> = listOf(
            ExerciseCondition("Condition 1", "Condition 1"),
            ExerciseCondition("Condition 2", "Condition 1"),
            ExerciseCondition("Condition 3", "Condition 1"),
            ExerciseCondition("Condition 4", "Condition 1"),
            ExerciseCondition("Condition 5", "Condition 1"),
        )
        _exerciseConditions.value = mockData
    }
}
