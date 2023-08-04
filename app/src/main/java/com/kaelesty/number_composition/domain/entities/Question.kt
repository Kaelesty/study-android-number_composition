package com.kaelesty.number_composition.domain.entities

data class Question(
    val sum: Int,
    val visibleNum: Int,
    val options: List<Int>
)