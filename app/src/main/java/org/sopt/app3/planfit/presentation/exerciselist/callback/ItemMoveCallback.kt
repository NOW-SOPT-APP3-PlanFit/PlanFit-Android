package org.sopt.app3.planfit.presentation.exerciselist.callback

import android.util.Log
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.domain.model.IndexInfo
import org.sopt.app3.planfit.presentation.exerciselist.adapter.ExerciseListAdapter

class ItemMoveCallback(
    private val adapter: ExerciseListAdapter,
    private val overlayView: View,
    private val recyclerView: RecyclerView,
    private val onClearView: (List<IndexInfo>) -> Unit
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        val fromPosition = viewHolder.bindingAdapterPosition
        val toPosition = target.bindingAdapterPosition
        adapter.moveItem(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        val finalList = adapter.getList()
        val fifinalList : MutableList<IndexInfo> = mutableListOf()
        finalList.withIndex().forEach {
            fifinalList.add(IndexInfo(id = it.value.id, index = it.index))
        }

        Log.e("리스트", fifinalList.toString())

        onClearView(fifinalList)

        viewHolder.itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
        overlayView.animate().alpha(0.0f).setDuration(200).start()
        recyclerView.children.forEach {
            val overlay = it.findViewById<View>(R.id.iv_exercise_list_item_overlay)
            overlay.animate().alpha(0.0f).setDuration(200).start()
        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.itemView?.let {
            it.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
        }
        overlayView.animate().alpha(0.2f).setDuration(200).start()
        recyclerView.children.forEach {
            if (it != viewHolder?.itemView) {
                val overlay = it.findViewById<View>(R.id.iv_exercise_list_item_overlay)
                overlay.animate().alpha(0.2f).setDuration(200).start()
            }
        }
    }
}