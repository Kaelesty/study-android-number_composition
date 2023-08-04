package com.kaelesty.number_composition.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaelesty.number_composition.domain.entities.GameSettings

class GameViewModelFactory(val application: Application, val settings: GameSettings): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(application, settings) as T
    }
}