package org.sopt.app3.planfit.domain.model

sealed class SetCount {
    abstract val setCnt: Int
    data class Completed(
        override val setCnt: Int
    ): SetCount()

    data class InProgress(
        override val setCnt: Int
    ): SetCount()

    data class Remaining(
        override val setCnt: Int
    ): SetCount()
}