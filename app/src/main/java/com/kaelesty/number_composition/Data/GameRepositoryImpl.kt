package com.kaelesty.number_composition.Data

import com.kaelesty.number_composition.Domain.Entities.GameSettings
import com.kaelesty.number_composition.Domain.Entities.Level
import com.kaelesty.number_composition.Domain.Entities.Question
import com.kaelesty.number_composition.Domain.Repository.GameRepository
import java.lang.Integer.max
import java.lang.Math.min
import kotlin.random.Random

class GameRepositoryImpl: GameRepository {
    override fun generateQuestion(maxSumValue: Int, ansVariantsCount: Int): Question {
        val sum = Random.nextInt(maxSumValue / 2, maxSumValue)
        val visibleNum = Random.nextInt(2, sum - 2)
        return Question(
            sum,
            visibleNum,
            generateOptions(sum, visibleNum, ansVariantsCount)
        )
    }

    private fun generateOptions(sum: Int, visibleNum: Int, variantsCount: Int): List<Int> {
        val options = hashSetOf<Int>()
        options.add(sum - visibleNum)
        while (options.size < variantsCount) {
            options.add(
                Random.nextInt(
                    max(sum - visibleNum - variantsCount, 1),
                    min(sum - 1, sum - visibleNum + 20)
                )
            )
        }
        // correct answer
        return options.toList()
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.LEVEL_TEST -> GameSettings(20, 1, 50, 5)
            Level.LEVEL_EASY -> GameSettings(50, 10, 50, 60)
            Level.LEVEL_MIDDLE -> GameSettings(500, 10, 75, 60)
            Level.LEVEL_HARD -> GameSettings(1000, 10, 85, 60)
            Level.LEVEL_HELL -> GameSettings(5000, 15, 100, 90)
        }
    }
}