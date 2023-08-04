package com.kaelesty.number_composition.presentation

import android.app.Application
import android.os.CountDownTimer
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kaelesty.number_composition.data.GameRepositoryImpl
import com.kaelesty.number_composition.domain.entities.GameSettings
import com.kaelesty.number_composition.domain.entities.Question
import com.kaelesty.number_composition.domain.use_cases.GenerateQuestionUseCase
import kotlinx.parcelize.Parcelize

class GameViewModel(application: Application, val settings: GameSettings) : AndroidViewModel(application) {

    private val _question: MutableLiveData<Question> = MutableLiveData()
    val question: LiveData<Question> get() = _question

    private val _corrAnsCountStat: MutableLiveData<Stat> = MutableLiveData()
    val corrAnsCountStat: LiveData<Stat> get() = _corrAnsCountStat

    private val _corrAnsPercentStat: MutableLiveData<Stat> = MutableLiveData()
    val corrAnsPercentStat: LiveData<Stat> get() = _corrAnsPercentStat

    private val _time: MutableLiveData<String> = MutableLiveData()
    val time: LiveData<String> get() = _time

    private val _gameOver: MutableLiveData<Boolean> = MutableLiveData()
    val gameOver: LiveData<Boolean> get() = _gameOver

    private val repoImpl = GameRepositoryImpl()
    private val generateQuestionUseCase: GenerateQuestionUseCase = GenerateQuestionUseCase(repoImpl)

    private var answersCount = 0
    private var correctAnswersCount = 0

    lateinit var timer: CountDownTimer

    fun gameBegin() {
        timer = object : CountDownTimer((settings.gameTimeSeconds * 1000).toLong(), 1000) {
            override fun onTick(p0: Long) {
                val minutes = (p0 / 1000) / 60
                val seconds = (p0 / 1000) % 60

                _time.value = "${if (minutes > 9) minutes else "0$minutes"}" +
                        ":${if (seconds > 9) seconds else "0$seconds"}"
            }

            override fun onFinish() {
                val percent = ((correctAnswersCount.toFloat() / answersCount.toFloat()) * 100).toInt()
                _gameOver.value = (
                        correctAnswersCount >= settings.minCorrectAnswersCount
                        && percent >= settings.minCorrectAnswersPercent
                        )
            }
        }.start()
        generateQuestion()
    }

    private fun generateQuestion() {
        updateStatistics()
        _question.value = generateQuestionUseCase(
            settings.maxSumValue
        )
    }

    fun cancelTimer() {
        timer.cancel()
    }

    private fun updateStatistics() {
        _corrAnsCountStat.value = Stat(
            "${correctAnswersCount}/${settings.minCorrectAnswersCount}",
            correctAnswersCount >= settings.minCorrectAnswersCount
        )

        val percent = ((correctAnswersCount.toFloat() / answersCount.toFloat()) * 100).toInt()
        _corrAnsPercentStat.value = Stat(
            "${percent}/${settings.minCorrectAnswersPercent}",
            percent >= settings.minCorrectAnswersPercent
        )
    }

    fun handleAnswer(sum: String, term: String, answer: String) {
        answersCount++

        if (sum.toInt() == term.toInt() + answer.toInt()) {
            correctAnswersCount++
        }

        generateQuestion()
    }


    @Parcelize data class Stat(val displayableString: String, val isPositive: Boolean) : Parcelable
}