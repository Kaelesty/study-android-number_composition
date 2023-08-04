package com.kaelesty.number_composition.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val maxSumValue: Int,
    val minCorrectAnswersCount: Int,
    val minCorrectAnswersPercent: Int,
    val gameTimeSeconds: Int
    ): Parcelable
