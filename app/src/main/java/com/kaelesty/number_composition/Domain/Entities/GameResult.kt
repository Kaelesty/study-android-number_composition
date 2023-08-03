package com.kaelesty.number_composition.Domain.Entities

import android.os.Parcelable
import com.kaelesty.number_composition.Presentation.GameViewModel
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameResult(
    val win: Boolean,
    val countStat: GameViewModel.Stat,
    val percentStat: GameViewModel.Stat
    ) : Parcelable