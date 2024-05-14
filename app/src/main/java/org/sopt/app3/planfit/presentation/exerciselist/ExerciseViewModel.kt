package org.sopt.app3.planfit.presentation.exerciselist

import androidx.lifecycle.ViewModel
import org.sopt.app3.planfit.domain.model.Exercise

class ExerciseViewModel : ViewModel() {
    val mock: List<Exercise> = listOf(
        Exercise("","랫 풀 다운1", "4x4"),
        Exercise("","랫 풀 다운2", "4x4"),
        Exercise("","랫 풀 다운3", "4x4"),
        Exercise("","랫 풀 다운4", "4x4"),
        Exercise("","랫 풀 다운5", "4x4"),
    )
}