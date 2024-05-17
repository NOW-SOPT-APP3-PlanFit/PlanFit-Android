package org.sopt.app3.planfit.presentation.exercisemain

import androidx.lifecycle.ViewModel
import org.sopt.app3.planfit.domain.model.SetCount

class SetViewModel:ViewModel() {
    var mockSetList: MutableList<SetCount> = mutableListOf(
        SetCount.InProgress(0, 1),
        SetCount.Remaining(1, 2),
        SetCount.Remaining(2, 3),
        SetCount.Remaining(3, 4),
    )
}