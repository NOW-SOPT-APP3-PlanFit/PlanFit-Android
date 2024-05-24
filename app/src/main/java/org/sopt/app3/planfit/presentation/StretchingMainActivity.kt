package org.sopt.app3.planfit.presentation

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.common.ViewModelFactory
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityStretchingMainBinding
import org.sopt.app3.planfit.presentation.exercisemain.ExerciseMainActivity

class StretchingMainActivity : BaseActivity<ActivityStretchingMainBinding>({ inflater ->
    ActivityStretchingMainBinding.inflate(inflater)
}) {
    private var timer: CountDownTimer? = null
    private var isTimerRunning = false
    private val totalTime = 5 * 60 * 1000
    private var timeElapsed = 0L
    private val viewModel by viewModels<StretchingMainViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivExerciseStretching.setImageResource(R.drawable.gif_exc_cycle)
        setUpTimer()

        binding.btnProgressStop.setOnClickListener {
            if (isTimerRunning) {
                stopTimer()
            } else {
                startTimer()
            }
            updateButtonImage()
        }
        binding.btnProgressNext.setOnClickListener {
            moveToExerciseMainActivity()
        }

        viewModel.time.flowWithLifecycle(lifecycle).onEach {
            binding.tvStopwatchNum.text = it
        }.launchIn(lifecycleScope)
    }

    private fun setUpTimer() {
        binding.pbProgressBar.max = totalTime
    }

    private fun moveToExerciseMainActivity() {
        val intent = Intent(
            this,
            ExerciseMainActivity::class.java
        )
        startActivity(intent)
    }

    private fun startTimer() {
        loadGif()
        val remainingTime = totalTime - timeElapsed
        timer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(untilFinished: Long) {
                timeElapsed = totalTime - untilFinished
                val secondsElapsed = timeElapsed / 1000
                val minutes = secondsElapsed / 60
                val seconds = secondsElapsed % 60

                binding.tvTimeMinute.text = String.format("%02d", minutes)
                binding.tvTimeSecond.text = String.format("%02d", seconds)
                binding.pbProgressBar.progress = timeElapsed.toInt()
            }

            override fun onFinish() {
                binding.tvTimeMinute.text = "03"
                binding.tvTimeSecond.text = "00"
                binding.pbProgressBar.progress = totalTime
                timeElapsed = 0
            }
        }
        timer?.start()
        isTimerRunning = true
        with(binding.ivExerciseStretching) {
            drawable.let {
                if (it is Animatable) {
                    it.start()
                }
            }
        }
    }

    private fun stopTimer() {
        timer?.cancel()
        isTimerRunning = false
        with(binding.ivExerciseStretching) {
            drawable.let {
                if (it is Animatable) {
                    it.stop()
                }
            }
        }
    }

    private fun loadGif() {
        val imageLoader = ImageLoader.Builder(this)
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()
        binding.ivExerciseStretching.load(R.raw.gif_exc_cycle, imageLoader = imageLoader)
    }

    private fun updateButtonImage() {
        val drawable = if (isTimerRunning) R.drawable.ic_pause__24 else R.drawable.ic_play__24
        binding.btnProgressStop.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawable)
    }
}