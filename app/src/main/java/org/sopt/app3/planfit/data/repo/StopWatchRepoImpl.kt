package org.sopt.app3.planfit.data.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.domain.repo.StopWatchRepo
import java.util.concurrent.TimeUnit

class StopWatchRepoImpl : StopWatchRepo {
    private var job: Job? = null
    private val _currentTime = MutableStateFlow("00:00:00")
    override val currentTime = _currentTime.asStateFlow()

    private var timeInSeconds = 0L

    override fun startStopWatch() {
        job?.cancel()
        job = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                timeInSeconds++
                _currentTime.value = formatTime(timeInSeconds)
                delay(1000)
            }
        }
    }

    override fun initStopWatch() {
        timeInSeconds = 0L
        _currentTime.value = formatTime(timeInSeconds)
    }

    override fun stopStopWatch() {
        job?.cancel()
    }

    private fun formatTime(timeInSeconds: Long): String {
        val hours = TimeUnit.SECONDS.toHours(timeInSeconds)
        val minutes = TimeUnit.SECONDS.toMinutes(timeInSeconds) % 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}