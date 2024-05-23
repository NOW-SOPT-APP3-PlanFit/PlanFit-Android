package org.sopt.app3.planfit.domain.repo

import kotlinx.coroutines.flow.Flow

interface StopWatchRepo {
    val currentTime: Flow<String>

    fun startStopWatch()
    fun initStopWatch()
    fun stopStopWatch()
}