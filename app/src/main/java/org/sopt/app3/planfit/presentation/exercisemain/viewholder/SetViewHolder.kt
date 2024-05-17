package org.sopt.app3.planfit.presentation.exercisemain.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.app3.planfit.databinding.ItemExerciseMainSetCompletedBinding
import org.sopt.app3.planfit.databinding.ItemExerciseMainSetInProgressBinding
import org.sopt.app3.planfit.databinding.ItemExerciseMainSetRemainingBinding
import org.sopt.app3.planfit.domain.model.SetCount

sealed class SetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    class SetCompletedViewHolder(private val binding: ItemExerciseMainSetCompletedBinding) : SetViewHolder(binding.root) {
        fun onBind(setData: SetCount.Completed) {
            binding.tvExerciseMainSetNum.text = setData.id.toString()
        }
    }

    class SetInProgressViewHolder(private val binding: ItemExerciseMainSetInProgressBinding) : SetViewHolder(binding.root) {
        fun onBind(setData: SetCount.InProgress) {
            binding.tvExerciseMainSetNum.text = setData.id.toString()
        }
    }

    class SetRemainingViewHolder(private val binding: ItemExerciseMainSetRemainingBinding) : SetViewHolder(binding.root) {
        fun onBind(setData: SetCount.Remaining) {
            binding.tvExerciseMainSetNum.text = setData.id.toString()
        }
    }
}