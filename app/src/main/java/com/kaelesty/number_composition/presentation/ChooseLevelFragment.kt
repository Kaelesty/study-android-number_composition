package com.kaelesty.number_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kaelesty.number_composition.data.GameRepositoryImpl
import com.kaelesty.number_composition.domain.entities.Level
import com.kaelesty.number_composition.domain.use_cases.GetGameSettingsUseCase
import com.kaelesty.number_composition.databinding.FragmentChooseLevelBinding

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding get() = _binding
        ?: throw RuntimeException("Binding is null")

    private lateinit var getGameSettingsUseCase: GetGameSettingsUseCase
    private val repoImpl: GameRepositoryImpl = GameRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getGameSettingsUseCase = GetGameSettingsUseCase(repoImpl)

        binding.buttonTestLevel.setOnClickListener {
            launchGameFragment(Level.LEVEL_TEST)
        }

        binding.buttonEasyLevel.setOnClickListener {
            launchGameFragment(Level.LEVEL_EASY)
        }

        binding.buttonMediumLevel.setOnClickListener {
            launchGameFragment(Level.LEVEL_MIDDLE)
        }

        binding.buttonHardLevel.setOnClickListener {
            launchGameFragment(Level.LEVEL_HARD)
        }

        binding.buttonHellLevel.setOnClickListener {
            launchGameFragment(Level.LEVEL_HELL)
        }
    }

    private fun launchGameFragment(level: Level) {
        var settings = getGameSettingsUseCase(level)

        findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(settings))
    }

    companion object {

    }
}