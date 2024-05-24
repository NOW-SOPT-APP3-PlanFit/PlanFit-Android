package org.sopt.app3.planfit.presentation.exercisetimelist.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.app3.planfit.databinding.ItemExerciseTimeListBinding
import org.sopt.app3.planfit.domain.model.ExerciseTime

class ExerciseTimeListViewHolder (
    private val binding:ItemExerciseTimeListBinding,
    private val onClick:(Int) -> Unit,
    private val selectedTime: String,
) : RecyclerView.ViewHolder(binding.root){
    fun onBind(data: ExerciseTime){
        binding.apply {
            if(data.time.contains(selectedTime)){
                vExerciseListEdge.isVisible = true
            }
            exerciseListTimeTitle.text = data.title
            exerciseListTime.text = data.time
            val num = data.time.filter { it.isDigit() }
            root.setOnClickListener {
                onClick(num.toInt())
            }
        }
    }
}