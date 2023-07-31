package com.kaelesty.number_composition.Presentation

import android.widget.TextView
import androidx.core.content.ContextCompat

class Stylist {
    companion object {
        fun updateStatColor(tv: TextView, isPositive: Boolean) {
            with(tv) {
                setTextColor(if (isPositive) {
                    ContextCompat.getColor(context, android.R.color.holo_green_light)
                }
                else {
                    ContextCompat.getColor(context, android.R.color.holo_red_light)
                })
            }
        }
    }
}