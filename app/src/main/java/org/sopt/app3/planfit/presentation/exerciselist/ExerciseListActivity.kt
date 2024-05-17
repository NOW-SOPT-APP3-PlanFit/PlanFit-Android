package org.sopt.app3.planfit.presentation.exerciselist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityExerciseListBinding
import org.sopt.app3.planfit.presentation.exerciselist.adapter.ExerciseListAdapter
import org.sopt.app3.planfit.presentation.exerciselist.callback.ItemMoveCallback

class ExerciseListActivity : BaseActivity<ActivityExerciseListBinding>({ ActivityExerciseListBinding.inflate(it) }) {
    private val viewModel by viewModels<ExerciseViewModel>()
    lateinit var adapter: ExerciseListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerViewAdapter()

        viewModel.getExercises()

        viewModel.exercises.observe(this){
            adapter.submitList(it)
        }

        binding.layoutExerciseListTop.ivExerciseListArrowDown.setOnClickListener {
            if(binding.layoutExerciseListTop.tvExerciseListDetail.isGone){
                binding.layoutExerciseListTop.ivExerciseListArrowDown.setImageResource(R.drawable.ic_arrow_top_24)
                binding.layoutExerciseListTop.tvExerciseListDetail.isVisible = true
            } else {
                binding.layoutExerciseListTop.ivExerciseListArrowDown.setImageResource(R.drawable.ic_arrow_down_24)
                binding.layoutExerciseListTop.tvExerciseListDetail.isGone = true
            }
        }

        binding.layoutExerciseListBottom.ivExerciseListArrowDown.setOnClickListener {
            if(binding.layoutExerciseListBottom.tvExerciseListDetail.isGone){
                binding.layoutExerciseListBottom.tvExerciseListDetail.isVisible = true
            } else {
                binding.layoutExerciseListBottom.tvExerciseListDetail.isGone = true
            }
        }
    }

    private fun initRecyclerViewAdapter() {
        adapter = ExerciseListAdapter {
            Log.e("e", "클릭")
        }
        binding.rvExerciseList.adapter = adapter
        val callback =
            ItemMoveCallback(adapter, binding.vExerciseListOverlay, binding.rvExerciseList){
                viewModel.changeExercisesIndex(it)
            }
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.rvExerciseList)
    }
}