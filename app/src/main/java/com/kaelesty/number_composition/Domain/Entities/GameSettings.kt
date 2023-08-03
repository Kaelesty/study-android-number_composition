package com.kaelesty.number_composition.Domain.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class GameSettings (
    val maxSumValue: Int,
    val minCorrectAnswersCount: Int,
    val minCorrectAnswersPercent: Int,
    val gameTimeSeconds: Int
    ): Parcelable
