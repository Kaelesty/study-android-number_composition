package com.kaelesty.number_composition.Domain.Entities

import com.kaelesty.number_composition.Presentation.GameViewModel
import java.io.Serializable

data class GameResult(
    val win: Boolean,
    val countStat: GameViewModel.Stat,
    val percentStat: GameViewModel.Stat
    ) : Serializable