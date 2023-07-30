package com.kaelesty.number_composition.Domain.Entities

import java.io.Serializable

data class GameSettings (
    val maxSumValue: Int,
    val minCorrectAnswersCount: Int,
    val minCorrectAnswersPercent: Int,
    val gameTimeSeconds: Int
    ): Serializable
