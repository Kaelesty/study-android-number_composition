package com.kaelesty.number_composition.domain.repository

import com.kaelesty.number_composition.domain.entities.GameSettings
import com.kaelesty.number_composition.domain.entities.Level
import com.kaelesty.number_composition.domain.entities.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        ansVariants: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}