package com.kaelesty.number_composition.Domain.Repository

import com.kaelesty.number_composition.Domain.Entities.GameSettings
import com.kaelesty.number_composition.Domain.Entities.Level
import com.kaelesty.number_composition.Domain.Entities.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        ansVariants: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}