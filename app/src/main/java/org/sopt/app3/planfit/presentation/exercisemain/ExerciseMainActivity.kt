package org.sopt.app3.planfit.presentation.exercisemain

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.common.ViewModelFactory
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityExerciseMainBinding
import org.sopt.app3.planfit.presentation.exercisemain.adapter.SetListAdapter

class ExerciseMainActivity : BaseActivity<ActivityExerciseMainBinding>({ inflater ->
    ActivityExerciseMainBinding.inflate(inflater)
}) {
    private val mainViewModel: ExerciseMainViewModel by viewModels { ViewModelFactory() }
    private val likeViewModel: LikeViewModel by viewModels { ViewModelFactory() }

    private lateinit var adapter: SetListAdapter
    private var lastIndex = 5
    private var startIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
        changeHeartState()

        adapter = SetListAdapter()

        binding.rvExerciseMainSet.adapter = adapter
        adapter.submitList(mainViewModel.setList.value)

        binding.rvExerciseMainSet.itemAnimator = null // 화면 깜빡임 방지
        binding.tvExerciseMainComplete.text = getString(R.string.exercise_main_complete)

        addSet()
        completeSet()
    }

    private fun addSet() {
        binding.tvExerciseMainAdd.setOnClickListener {
            mainViewModel.addExerciseSet(lastIndex++.toLong())
        }
        mainViewModel.setList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun completeSet() {
        binding.tvExerciseMainComplete.setOnClickListener {
            mainViewModel.completeExerciseSet(startIndex++.toLong())

            if(startIndex == lastIndex-1)
                binding.tvExerciseMainComplete.isEnabled = false
        }

        mainViewModel.setList.observe(this) {
            binding.tvExerciseMainComplete.isEnabled = true
        }

        mainViewModel.currentIndex.observe(this) {
            adapter.submitList(mainViewModel.setList.value)
            adapter.notifyItemChanged((it - 1).toInt())
            adapter.notifyItemChanged(it.toInt())

            binding.tvExerciseMainComplete.text = "${it} 세트 완료"
        }
    }

    private fun gifLoad() {
        val imageLoader = ImageLoader.Builder(this)
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()

        binding.ivExerciseMainLatpulldown.load(R.raw.gif_exc_latpulldown, imageLoader = imageLoader)
    }

    private fun changeHeartState() {
        with(binding.ivExerciseMainHeart) {
            setOnClickListener {
                isSelected = !isSelected

                if(isSelected)
                    likeViewModel.changeToLike(1)
                else
                    likeViewModel.changeToUnlike(1)
            }
        }
    }
}