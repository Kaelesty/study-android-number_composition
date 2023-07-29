package com.kaelesty.number_composition.Domain.UseCases

import com.kaelesty.number_composition.Domain.Entities.GameSettings
import com.kaelesty.number_composition.Domain.Entities.Level
import com.kaelesty.number_composition.Domain.Repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}