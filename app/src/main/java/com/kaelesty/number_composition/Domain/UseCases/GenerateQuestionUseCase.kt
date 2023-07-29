package com.kaelesty.number_composition.Domain.UseCases

import com.kaelesty.number_composition.Domain.Entities.Question
import com.kaelesty.number_composition.Domain.Repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, ANSWER_VARIANTS_COUNT)
    }

    companion object {
        const val ANSWER_VARIANTS_COUNT = 6
    }
}