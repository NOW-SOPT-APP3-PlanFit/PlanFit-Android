package org.sopt.app3.planfit.presentation.exercisemain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.app3.planfit.core.ui.view.ItemDiffCallback
import org.sopt.app3.planfit.databinding.ItemExerciseMainSetCompletedBinding
import org.sopt.app3.planfit.databinding.ItemExerciseMainSetInProgressBinding
import org.sopt.app3.planfit.databinding.ItemExerciseMainSetRemainingBinding
import org.sopt.app3.planfit.domain.model.SetCount
import org.sopt.app3.planfit.presentation.exercisemain.viewholder.SetViewHolder

class SetListAdapter : ListAdapter<SetCount, SetViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ViewType.COMPLETED_VIEW_TYPE -> {
                val binding = ItemExerciseMainSetCompletedBinding.inflate(inflater, parent, false)
                SetViewHolder.SetCompletedViewHolder(binding)
            }

            ViewType.IN_PROGRESS_VIEW_TYPE -> {
                val binding = ItemExerciseMainSetInProgressBinding.inflate(inflater, parent, false)
                SetViewHolder.SetInProgressViewHolder(binding)
            }

            ViewType.REMAINING_VIEW_TYPE -> {
                val binding = ItemExerciseMainSetRemainingBinding.inflate(inflater, parent, false)
                SetViewHolder.SetRemainingViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        when (holder) {
            is SetViewHolder.SetCompletedViewHolder -> {
                holder.onBind(getItem(position) as SetCount.Completed)
            }

            is SetViewHolder.SetInProgressViewHolder -> {
                holder.onBind(getItem(position) as SetCount.InProgress)
            }

            is SetViewHolder.SetRemainingViewHolder -> {
                holder.onBind(getItem(position) as SetCount.Remaining)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is SetCount.Completed -> ViewType.COMPLETED_VIEW_TYPE
            is SetCount.InProgress -> ViewType.IN_PROGRESS_VIEW_TYPE
            is SetCount.Remaining -> ViewType.REMAINING_VIEW_TYPE
        }
    }


    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<SetCount>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new}
        )
    }
}

class ViewType {
    companion object {
        const val COMPLETED_VIEW_TYPE = 0
        const val IN_PROGRESS_VIEW_TYPE = 1
        const val REMAINING_VIEW_TYPE = 2
    }
}