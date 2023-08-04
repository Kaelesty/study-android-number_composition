package com.kaelesty.number_composition.domain.use_cases

import com.kaelesty.number_composition.domain.entities.GameSettings
import com.kaelesty.number_composition.domain.entities.Level
import com.kaelesty.number_composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}