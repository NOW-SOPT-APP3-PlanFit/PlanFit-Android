package org.sopt.app3.planfit.presentation.exercisetimelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.model.ExerciseTime
import org.sopt.app3.planfit.domain.repo.MainRepository

class ExerciseTimeListViewModel(private var mainRepository: MainRepository) : ViewModel() {
    private val _setTime = MutableLiveData(
        mutableListOf(
            ExerciseTime("짧게", "약 29분"),
            ExerciseTime("조금 짧게", "약 44분"),
            ExerciseTime("보통", "약 58분"),
            ExerciseTime("조금 길게", "약 73분"),
            ExerciseTime("길게", "약 87분"),
            ExerciseTime("아주 길게", "약 116분"),
        )
    )
    private val _exerciseTime = MutableLiveData<List<ExerciseTime>>()
    val exerciseTime:LiveData<List<ExerciseTime>> get() = _exerciseTime

    fun getMain() = viewModelScope.launch{
        mainRepository.getMain()
            .onSuccess {
                }
            .onFailure {
                Log.e("getCondition fail", it.message.toString())
            }

            }
    }
