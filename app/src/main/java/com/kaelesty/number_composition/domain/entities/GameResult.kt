package com.kaelesty.number_composition.domain.entities

import android.os.Parcelable
import com.kaelesty.number_composition.presentation.GameViewModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val win: Boolean,
    val countStat: GameViewModel.Stat,
    val percentStat: GameViewModel.Stat
    ) : Parcelable