package org.sopt.app3.planfit.domain.model

data class Exercise(
    val id: Int,
    val imageUri: String,
    val title: String,
    val set: Int,
    val weight: Int,
    val count: Int,
    val index: Int,
)
