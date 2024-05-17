package org.sopt.app3.planfit.domain.model

sealed class SetCount {
    abstract val id: Int

    data class Completed(
        override val id: Int,
        val setCnt: Int
    ): SetCount()

    data class InProgress(
        override val id: Int,
        val setCnt: Int
    ): SetCount()

    data class Remaining(
        override val id: Int,
        val setCnt: Int
    ): SetCount()
}