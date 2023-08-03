package com.kaelesty.number_composition.Presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.kaelesty.number_composition.Data.GameRepositoryImpl
import com.kaelesty.number_composition.Domain.Entities.GameResult
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

    private lateinit var viewModel: GameViewModel

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

        initViewModel()
        viewModel.gameBegin()
    }

    private fun initViewModel() {
        viewModel = GameViewModelFactory(
            requireActivity().application,
            GameFragmentArgs.fromBundle(requireArguments()).gameSettings
        )
            .create(GameViewModel::class.java)
        with(binding) {
            with(viewModel) {
                question.observe(requireActivity()) {
                    tvSum.text = it.sum.toString()
                    tvTerm.text = it.visibleNum.toString()

                    val variants = listOf(variant1, variant2, variant3, variant4, variant5, variant6)
                    for ((i, variant) in variants.withIndex()) {
                        variant.text = it.options[i].toString()
                        variant.setOnClickListener {
                            viewModel.handleAnswer(
                                tvSum.text as String,
                                tvTerm.text as String,
                                variant.text as String
                            )
                        }
                    }
                }

                corrAnsCountStat.observe(requireActivity()) {
                    tvCorrectAnsCount.text = it.displayableString
                    Stylist.updateStatColor(tvCorrectAnsCount, it.isPositive)
                }

                corrAnsPercentStat.observe(requireActivity()) {
                    tvCorrectAnsPercent.text = it.displayableString
                    Stylist.updateStatColor(tvCorrectAnsPercent, it.isPositive)
                }

                time.observe(requireActivity()) {
                    tvTimer.text = it
                }

                gameOver.observe(requireActivity()) {
                    gameOver(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelTimer()
    }

    private fun gameOver(win: Boolean) {

        val result = GameResult(
            win,
            viewModel.corrAnsCountStat.value ?: throw RuntimeException("CountStat is null"),
            viewModel.corrAnsPercentStat.value ?: throw RuntimeException("PercentStat is null")
        )

        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(result))
    }

    companion object {

        const val BUNDLE_TAG_SETTINGS = "settings"

    }
}