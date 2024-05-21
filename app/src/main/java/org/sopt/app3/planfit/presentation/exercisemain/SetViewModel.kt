package org.sopt.app3.planfit.presentation.exercisemain

import androidx.lifecycle.ViewModel
import org.sopt.app3.planfit.domain.model.SetCount

class SetViewModel:ViewModel() {
    var currentIndex = 0
    var setCnt = 1

    val mockSetList: MutableList<SetCount> = mutableListOf(
        SetCount.InProgress(1),
        SetCount.Remaining(2),
        SetCount.Remaining(3),
        SetCount.Remaining(4),
    )
}