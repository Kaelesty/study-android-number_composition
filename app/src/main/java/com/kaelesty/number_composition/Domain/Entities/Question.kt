package com.kaelesty.number_composition.Domain.Entities

data class Question(
    val sum: Int,
    val visibleNum: Int,
    val options: List<Int>
)