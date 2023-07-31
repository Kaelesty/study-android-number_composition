package com.kaelesty.number_composition.Presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaelesty.number_composition.Domain.Entities.GameSettings

class GameViewModelFactory(val application: Application, val settings: GameSettings): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(application, settings) as T
    }
}