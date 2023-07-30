package com.kaelesty.number_composition.Presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kaelesty.number_composition.Data.GameRepositoryImpl
import com.kaelesty.number_composition.Domain.Entities.GameSettings
import com.kaelesty.number_composition.Domain.Entities.Question
import com.kaelesty.number_composition.Domain.UseCases.GenerateQuestionUseCase
import com.kaelesty.number_composition.R
import com.kaelesty.number_composition.databinding.FragmentChooseLevelBinding
import com.kaelesty.number_composition.databinding.FragmentGameBinding
import com.kaelesty.number_composition.databinding.FragmentWelcomeBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding
        ?: throw RuntimeException("Binding is null")

    private lateinit var settings: GameSettings
    private lateinit var generateQuestionUseCase: GenerateQuestionUseCase

    private lateinit var currentQuestion: Question

    private val repoImpl: GameRepositoryImpl = GameRepositoryImpl()

    private var answerCount: Int = 0
    private var correctAnswerCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settings = requireArguments().getSerializable(BUNDLE_TAG_SETTINGS) as GameSettings

        generateQuestionUseCase = GenerateQuestionUseCase(repoImpl)

        setupQuestion()
    }

    fun handleAnswer(pickedVariant: Int) {
        answerCount++

        var isAnswerCorrect: Boolean

        with(currentQuestion) {
            isAnswerCorrect = sum == options[pickedVariant] + visibleNum
        }

        if (isAnswerCorrect) {
            correctAnswerCount++
        }

        setupQuestion()
    }


    private fun setupQuestion() {
        currentQuestion = generateQuestionUseCase(settings.maxSumValue)

        with(binding) {

            tvSum.text = currentQuestion.sum.toString()
            tvTerm.text = currentQuestion.visibleNum.toString()

            with(tvCorrectAnsCount) {
                text = "${correctAnswerCount.toString()}/${settings.minCorrectAnswersCount}"
                updateStatColor(this@with, correctAnswerCount >= settings.minCorrectAnswersCount)
            }

            with(tvCorrectAnsPercent) {
                val percent: Int = ((correctAnswerCount.toFloat() / answerCount.toFloat()) * 100).toInt()
                text = "${percent.toString()}/${settings.minCorrectAnswersPercent}"
                updateStatColor(this@with, percent >= settings.minCorrectAnswersPercent)
            }

            with(variant1) {
                text = currentQuestion.options[0].toString()
                setOnClickListener {
                    handleAnswer(0)
                }
            }

            with(variant2){
                text = currentQuestion.options[1].toString()
                setOnClickListener {
                    handleAnswer(1)
                }
            }

            with(variant3){
                text = currentQuestion.options[2].toString()
                setOnClickListener {
                    handleAnswer(3)
                }
            }

            with(variant4){
                text = currentQuestion.options[3].toString()
                setOnClickListener {
                    handleAnswer(3)
                }
            }

            with(variant5){
                text = currentQuestion.options[4].toString()
                setOnClickListener {
                    handleAnswer(4)
                }
            }

            with(variant6){
                text = currentQuestion.options[5].toString()
                setOnClickListener {
                    handleAnswer(5)
                }
            }

        }
    }

    private fun updateStatColor(tv: TextView, isPositive: Boolean) {
        with(tv) {
            setTextColor(if (isPositive) {
                ContextCompat.getColor(context, android.R.color.holo_green_light)
            }
            else {
                ContextCompat.getColor(context, android.R.color.holo_red_light)
            })
        }
    }

    companion object {

        private const val BUNDLE_TAG_SETTINGS = "settings"

        fun newInstance(settings: GameSettings): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BUNDLE_TAG_SETTINGS, settings)
                }
            }
        }
    }
}