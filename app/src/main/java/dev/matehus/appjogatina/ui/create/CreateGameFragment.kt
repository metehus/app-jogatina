package dev.matehus.appjogatina.ui.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.matehus.appjogatina.R
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.databinding.FragmentCreateGameBinding
import dev.matehus.appjogatina.databinding.FragmentGameDetailsBinding
import dev.matehus.appjogatina.databinding.FragmentNotificationsBinding

class CreateGameFragment : Fragment() {

    companion object {
        fun newInstance() = CreateGameFragment()
    }

    private val viewModel by activityViewModels<CreateGameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateGameBinding.inflate(inflater, container, false)

        binding.createButton.setOnClickListener {
            val name = binding.gameNameInput.text.toString()
            val imageUrl = binding.gameImageInput.text.toString()

            val game = Game(name, imageUrl)

            viewModel.create(game)
            val action = CreateGameFragmentDirections.actionCreateGameFragmentToNavigationHome()
            findNavController().navigate(action)
        }

        return binding.root
    }
}