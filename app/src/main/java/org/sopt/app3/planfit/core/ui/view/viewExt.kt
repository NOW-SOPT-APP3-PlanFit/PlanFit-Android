package org.sopt.app3.planfit.core.ui.view

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: () -> String) {
    Snackbar.make(this, message(), Snackbar.LENGTH_SHORT).show()
}

open class ItemDiffCallback<T : Any>(
    val onItemsTheSame: (T, T) -> Boolean,
    val onContentsTheSame: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean = onItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean = onContentsTheSame(oldItem, newItem)
}
