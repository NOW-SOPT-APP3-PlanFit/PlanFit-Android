package org.sopt.app3.planfit

import android.app.Application
import org.sopt.app3.planfit.data.repo.StopWatchRepoImpl
import org.sopt.app3.planfit.domain.repo.StopWatchRepo

class PlanFitApp: Application() {
    companion object{
        val stopWatch: StopWatchRepo by lazy {
            StopWatchRepoImpl()
        }
    }
}