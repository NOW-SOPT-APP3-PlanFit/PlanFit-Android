package org.sopt.app3.planfit.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.data.repo.StopWatchRepoImpl
import org.sopt.app3.planfit.databinding.ActivityStopwatchBinding
import org.sopt.app3.planfit.domain.repo.StopWatchRepo

class StopwatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStopwatchBinding
    lateinit var stopWatchRepo: StopWatchRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopwatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stopWatchRepo = StopWatchRepoImpl()

        stopWatchRepo.currentTime.flowWithLifecycle(lifecycle).onEach {
            binding.tvStopwatchNum.text = it
        }.launchIn(lifecycleScope)

        stopWatchRepo.startStopWatch()
    }
}