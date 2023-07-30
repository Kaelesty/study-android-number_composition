package com.kaelesty.number_composition.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaelesty.number_composition.R
import com.kaelesty.number_composition.databinding.FragmentChooseLevelBinding
import com.kaelesty.number_composition.databinding.FragmentGameBinding
import com.kaelesty.number_composition.databinding.FragmentWelcomeBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding
        ?: throw RuntimeException("Binding is null")

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

    }

    companion object {

        fun newInstance() =
            GameFragment()
    }
}