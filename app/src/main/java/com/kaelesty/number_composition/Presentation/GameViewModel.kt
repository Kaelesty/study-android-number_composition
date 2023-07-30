package com.kaelesty.number_composition.Presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kaelesty.number_composition.Domain.Entities.Question

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val _question: MutableLiveData<Question> = MutableLiveData()
    val question: LiveData<Question> get() = _question

    fun generateQuestion() {

    }
}