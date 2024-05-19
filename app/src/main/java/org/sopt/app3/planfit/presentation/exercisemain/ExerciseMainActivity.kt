package org.sopt.app3.planfit.presentation.exercisemain

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityExerciseMainBinding
import org.sopt.app3.planfit.domain.model.SetCount
import org.sopt.app3.planfit.presentation.exercisemain.adapter.SetListAdapter

class ExerciseMainActivity : BaseActivity<ActivityExerciseMainBinding>({ inflater ->
    ActivityExerciseMainBinding.inflate(inflater)
}) {
    private val viewModel by viewModels<SetViewModel>()
    private lateinit var adapter: SetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
        changeHeartState()

        adapter = SetListAdapter()

        binding.rvExerciseMainSet.adapter = adapter
        adapter.submitList(viewModel.mockSetList)

        binding.tvExerciseMainComplete.text = getString(R.string.exercise_main_complete)

        completeSet()
        addSet()
    }

    private fun completeSet() {
        binding.tvExerciseMainComplete.setOnClickListener {
            viewModel.mockSetList[viewModel.currentIndex] = SetCount.Completed(viewModel.setCnt)
            viewModel.mockSetList[++viewModel.currentIndex] = SetCount.InProgress(++viewModel.setCnt)

            adapter.submitList(viewModel.mockSetList)
            adapter.notifyItemChanged(viewModel.currentIndex - 1)
            adapter.notifyItemChanged(viewModel.currentIndex)

            binding.tvExerciseMainComplete.text = "${viewModel.currentIndex + 1} 세트 완료"
        }
    }

    private fun addSet() {
        binding.tvExerciseMainAdd.setOnClickListener{
            viewModel.mockSetList.add(SetCount.Remaining(viewModel.mockSetList.size + 1))

            adapter.submitList(viewModel.mockSetList)
            adapter.notifyItemChanged(viewModel.currentIndex)
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