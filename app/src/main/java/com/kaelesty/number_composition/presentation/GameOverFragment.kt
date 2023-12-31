package com.kaelesty.number_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kaelesty.number_composition.R
import com.kaelesty.number_composition.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null
    private val binding: FragmentGameOverBinding get() = _binding
        ?: throw RuntimeException("Binding is bull")

    val IMAGE_RESOURCE_STICKER_WIN = R.drawable.game_over_win
    val IMAGE_RESOURCE_STICKER_LOSE = R.drawable.game_over_lose

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            gameResult = GameOverFragmentArgs.fromBundle(requireArguments()).gameResult

            buttonGameBegin.setOnClickListener {
                findNavController().navigate(R.id.action_gameOverFragment_to_chooseLevelFragment)
            }
        }
    }

    companion object {

        const val BUNDLE_TAG_GAME_RESULT = "game_result"

    }
}