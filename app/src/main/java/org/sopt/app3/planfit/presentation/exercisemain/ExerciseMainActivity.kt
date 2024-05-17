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

    private var currentIndex = 0
    private var setCnt = 1

    private var mockSetList: MutableList<SetCount> = mutableListOf(
        SetCount.InProgress(0, 1),
        SetCount.Remaining(1, 2),
        SetCount.Remaining(2, 3),
        SetCount.Remaining(3, 4),
    )

    private lateinit var adapter: SetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifLoad()
        changeHeartState()

        adapter = SetListAdapter()

        binding.rvExerciseMainSet.adapter = adapter
        adapter.submitList(mockSetList)

        binding.tvExerciseMainComplete.text = getString(R.string.exercise_main_complete)

        completeSet()
        addSet()
    }

    private fun completeSet() {
        binding.tvExerciseMainComplete.setOnClickListener {
            mockSetList[currentIndex] = SetCount.Completed(currentIndex, setCnt)
            mockSetList[++currentIndex] = SetCount.InProgress(currentIndex, ++setCnt)

            adapter.submitList(mockSetList)
            adapter.notifyItemChanged(currentIndex - 1)
            adapter.notifyItemChanged(currentIndex)

            binding.tvExerciseMainComplete.text = "${currentIndex + 1} 세트 완료"
        }
    }

    private fun addSet() {
        binding.tvExerciseMainAdd.setOnClickListener{
            mockSetList.add(SetCount.Remaining(mockSetList.size - 1, mockSetList.size + 1))

            adapter.submitList(mockSetList)
            adapter.notifyItemChanged(currentIndex)
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