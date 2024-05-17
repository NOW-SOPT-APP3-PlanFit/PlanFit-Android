package org.sopt.app3.planfit.presentation.exercisetimelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.app3.planfit.core.ui.view.ItemDiffCallback
import org.sopt.app3.planfit.databinding.ItemExerciseTimeListBinding
import org.sopt.app3.planfit.domain.model.ExerciseTime
import org.sopt.app3.planfit.presentation.exercisetimelist.viewholder.ExerciseTimeListViewHolder

class ExerciseTimeListAdapter(
    private val onClick: (Int) -> Unit,
) : ListAdapter<ExerciseTime, ExerciseTimeListViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseTimeListViewHolder {
        return ExerciseTimeListViewHolder(
            ItemExerciseTimeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ExerciseTimeListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun getList(): List<ExerciseTime> {
        return currentList.toList()
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ExerciseTime>(
            onItemsTheSame = { old, new -> old.title == new.title },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}