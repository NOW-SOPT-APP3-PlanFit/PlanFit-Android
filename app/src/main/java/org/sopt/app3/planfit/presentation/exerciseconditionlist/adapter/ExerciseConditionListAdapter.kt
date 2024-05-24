package org.sopt.app3.planfit.presentation.exercisetimelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.app3.planfit.core.ui.view.ItemDiffCallback
import org.sopt.app3.planfit.databinding.ItemExerciseConditionListBinding
import org.sopt.app3.planfit.databinding.ItemExerciseTimeListBinding
import org.sopt.app3.planfit.domain.model.ExerciseCondition
import org.sopt.app3.planfit.domain.model.ExerciseTime
import org.sopt.app3.planfit.presentation.exerciseconditionlist.viewholder.ExerciseConditionListViewHolder
import org.sopt.app3.planfit.presentation.exercisetimelist.viewholder.ExerciseTimeListViewHolder

class ExerciseConditionListAdapter(
    private var selectedCondition: String?,
    private val onClick: (String) -> Unit,
) : ListAdapter<ExerciseCondition, ExerciseConditionListViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseConditionListViewHolder {
        return ExerciseConditionListViewHolder(
            binding = ItemExerciseConditionListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick,
            selectedCondition = selectedCondition
        )
    }


    override fun onBindViewHolder(holder: ExerciseConditionListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun getList(): List<ExerciseCondition> {
        return currentList.toList()
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ExerciseCondition>(
            onItemsTheSame = { old, new -> old.title == new.title },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}