package org.sopt.app3.planfit.presentation.exercisemain

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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
    private val viewModel: ExerciseMainViewModel by viewModels{ ViewModelFactory() }
    private lateinit var adapter: SetListAdapter
    private var lastIndex = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
        changeHeartState()

        adapter = SetListAdapter()

        binding.rvExerciseMainSet.adapter = adapter
        adapter.submitList(viewModel.setList.value)

        binding.tvExerciseMainComplete.text = getString(R.string.exercise_main_complete)

        completeSet()
        addSet()
    }

    private fun completeSet() {
        binding.tvExerciseMainComplete.setOnClickListener {
           //  viewModel.completeSet()
        }

        viewModel.currentIndex.observe(this){
            adapter.submitList(viewModel.setList.value)
            adapter.notifyItemChanged((it - 1).toInt())
            adapter.notifyItemChanged(it.toInt())

            binding.tvExerciseMainComplete.text = "${it + 1} 세트 완료"
        }
    }

    private fun addSet() {
        binding.tvExerciseMainAdd.setOnClickListener{
           viewModel.addExerciseSet(lastIndex++.toLong())
        }
        viewModel.setList.observe(this){
            Log.e("로그",it.toString())
            adapter.submitList(it)
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
            }
        }
    }
}