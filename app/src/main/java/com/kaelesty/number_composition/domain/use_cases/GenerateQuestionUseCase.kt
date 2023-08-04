package com.kaelesty.number_composition.domain.use_cases

import com.kaelesty.number_composition.domain.entities.Question
import com.kaelesty.number_composition.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, ANSWER_VARIANTS_COUNT)
    }

    companion object {
        const val ANSWER_VARIANTS_COUNT = 6
    }
}