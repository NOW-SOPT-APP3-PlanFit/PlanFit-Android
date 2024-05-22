package org.sopt.app3.planfit.presentation.exerciselist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.sopt.app3.planfit.core.ui.view.ItemDiffCallback
import org.sopt.app3.planfit.databinding.ItemExerciseListMuscleBinding
import org.sopt.app3.planfit.domain.model.Exercise
import org.sopt.app3.planfit.presentation.exerciselist.viewholder.ExerciseViewHolder

class ExerciseListAdapter(
    private val onClick: () -> Unit
) : ListAdapter<Exercise, ExerciseViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseListMuscleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun getList() : List<Exercise> {
        return currentList.toList()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val currentList = currentList.toMutableList()
        val item = currentList.removeAt(fromPosition)
        currentList.add(toPosition, item)

        submitList(currentList)
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<Exercise>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}