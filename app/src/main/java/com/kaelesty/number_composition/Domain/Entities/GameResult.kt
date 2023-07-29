package com.kaelesty.number_composition.Domain.Entities

data class GameResult(
    val win: Boolean,
    val correctAnswersCount: Int,
    val correctAnswersPercent: Int,
    val answeredQuestionsCount: Int,
    val gameSettings: GameSettings
    )