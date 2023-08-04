package com.kaelesty.number_composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kaelesty.number_composition.R

@BindingAdapter("stringToDisplay")
fun bindStatTrackerText(tv: TextView, string: String) {
	tv.text = string
}

@BindingAdapter("isPositive")
fun bindStatTrackerColor(tv: TextView, isPositive: Boolean) {
	Stylist.updateStatColor(tv, isPositive)
}

@BindingAdapter("stickerType")
fun bindStickerSrc(imageView: ImageView, win: Boolean) {
	imageView.setImageResource(
		if (win) R.drawable.game_over_win else R.drawable.game_over_lose
	)
}

@BindingAdapter("verdict")
fun bindVerdict(tv: TextView, win: Boolean) {
	tv.text = if (win) "Победа" else "Поражение"
}