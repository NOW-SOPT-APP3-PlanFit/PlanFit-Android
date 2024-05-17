package org.sopt.app3.planfit.presentation.exercisemain

import android.os.Bundle
import android.widget.ImageView
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

    private var currentIndex = 1
    private var mockSetList: MutableList<SetCount> = mutableListOf(
        SetCount.InProgress(1),
        SetCount.Remaining(2),
        SetCount.Remaining(3),
        SetCount.Remaining(4),
    )

    private lateinit var adapter: SetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
        changeHeartState()

        adapter = SetListAdapter()

        binding.rvExerciseMainSet.adapter = adapter
        adapter.submitList(mockSetList)

        completeSet()
    }

    private fun completeSet() {
        binding.tvExerciseMainComplete.setOnClickListener {
            mockSetList[currentIndex] = SetCount.Completed(currentIndex)
            mockSetList[++currentIndex] = SetCount.InProgress(currentIndex)
            adapter.submitList(mockSetList)
            adapter.notifyItemChanged(currentIndex - 1)
            adapter.notifyItemChanged(currentIndex)

            binding.tvExerciseMainComplete.text = getString(currentIndex, R.string.exercise_main_complete)
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
        val ivHeart: ImageView = binding.ivExerciseMainHeart
        ivHeart.setOnClickListener {
            ivHeart.isSelected = !ivHeart.isSelected
        }
    }
}