package org.sopt.app3.planfit.presentation.exercisetimelist.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.app3.planfit.databinding.ItemExerciseTimeListBinding
import org.sopt.app3.planfit.domain.model.ExerciseTime

class ExerciseTimeListViewHolder (
    private val binding:ItemExerciseTimeListBinding,
    private val onClick:(Int) -> Unit,
    private val selectedTime: Int,
) : RecyclerView.ViewHolder(binding.root){
    init {
        binding.root.setOnClickListener {
            onClick
        }
    }
    fun onBind(data: ExerciseTime){
        binding.apply {
            if(selectedTime.toString() == data.time){
                vExerciseListEdge.isVisible = true
            }
            exerciseListTimeTitle.text = data.title
            exerciseListTime.text = data.time
        }
    }
}