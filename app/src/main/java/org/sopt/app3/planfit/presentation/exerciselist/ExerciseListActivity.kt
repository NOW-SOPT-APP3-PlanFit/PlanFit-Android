package org.sopt.app3.planfit.presentation.exerciselist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.common.ViewModelFactory
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityExerciseListBinding
import org.sopt.app3.planfit.databinding.ItemExerciseListStretchBinding
import org.sopt.app3.planfit.presentation.exerciselist.adapter.ExerciseListAdapter
import org.sopt.app3.planfit.presentation.exerciselist.callback.ItemMoveCallback

class ExerciseListActivity :
    BaseActivity<ActivityExerciseListBinding>({ ActivityExerciseListBinding.inflate(it) }) {
    private val viewModel by viewModels<ExerciseViewModel> { ViewModelFactory() }
    lateinit var adapter: ExerciseListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerViewAdapter()
        observeExercises()
        initViews()
    }

    private fun initRecyclerViewAdapter() {
        adapter = ExerciseListAdapter {
            Log.e("e", "클릭")
        }
        binding.rvExerciseList.adapter = adapter
        val callback = ItemMoveCallback(
            adapter,
            binding.vExerciseListOverlay,
            binding.rvExerciseList
        ) { newIndex ->
            viewModel.changeExercisesIndex(newIndex)
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.rvExerciseList)
    }

    private fun observeExercises() {
        viewModel.getExercises()
        viewModel.exercises.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun initViews() {
        with(binding) {
            layoutExerciseListTop.configureExerciseList(
                "웜업 스트레칭",
                "6개의 스트레칭",
                "싸이클\n로테이팅 허리 스트레칭\n후면 어깨 스트레칭\n어깨/등 스트레칭\n원 암 가슴 스트레칭\n크로스오버 힙/고관절 스트레칭"
            )
            layoutExerciseListBottom.configureExerciseList(
                "쿨다운 스트레칭",
                "4개의 스트레칭",
                "후면 어깨 스트레칭\n오버헤드 등 스트레칭\n비하인드 암 가슴 스트레칭\n스탠딩 힙/고관절 스트레칭"
            )
        }
    }

    private fun ItemExerciseListStretchBinding.configureExerciseList(
        title: String,
        subtitle: String,
        detail: String,
    ) {
        tvExerciseListTitle.text = title
        tvExerciseListSubtitle.text = subtitle
        tvExerciseListDetail.text = detail
        ivExerciseListArrowDown.setOnClickListener {
            val isVisible = tvExerciseListDetail.isGone
            ivExerciseListArrowDown.setImageResource(if (isVisible) R.drawable.ic_arrow_top_24 else R.drawable.ic_arrow_down_24)
            tvExerciseListDetail.isGone = !isVisible
        }
    }
}