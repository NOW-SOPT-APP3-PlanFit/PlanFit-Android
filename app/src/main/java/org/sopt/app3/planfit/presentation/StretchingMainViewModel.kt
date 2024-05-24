package org.sopt.app3.planfit.presentation

import androidx.lifecycle.ViewModel
import org.sopt.app3.planfit.domain.repo.StopWatchRepo

class StretchingMainViewModel(
    private val stopWatch: StopWatchRepo
) : ViewModel() {
    val time = stopWatch.currentTime
}