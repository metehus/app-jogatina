package dev.matehus.appjogatina.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import dev.matehus.appjogatina.R
import dev.matehus.appjogatina.data.game.GameStatus
import dev.matehus.appjogatina.databinding.FragmentGameDetailsBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {
    private val viewModel by activityViewModels<GameDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameDetailsBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.game.collect {
                val game = viewModel.game.value
                if (game != null) {
                    binding.textGameName.text = it?.name ?: "Carregando..."
                    binding.detailsGameImage.load(it?.imageUrl)
                    binding.detailsRatingBar.rating = game.rating ?: 0f

                    ArrayAdapter.createFromResource(
                        requireContext(),
                        R.array.game_status_list,
                        android.R.layout.simple_spinner_item,
                    ).also { adapter ->
                        binding.detailsStatusSelect.adapter = adapter
                    }
                    binding.detailsStatusSelect.setSelection(GameStatus.values().indexOf(game.status))

                    binding.libraryToggleDetails.setImageResource(
                        if (game.addedToLibrary) R.drawable.baseline_check_24 else R.drawable.baseline_add_24
                    )

                    // updates
                    binding.detailsStatusSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            index: Int,
                            p3: Long
                        ) {
                            game.status = GameStatus.values()[index]
                            viewModel.update(game)
                        }
                        override fun onNothingSelected(p0: AdapterView<*>?) {}
                    }

                    binding.detailsRatingBar.setOnRatingBarChangeListener { _, value, _ ->
                        game.rating = value
                        Log.d("JGTN", "$value, $game")
                        viewModel.update(game)
                    }
                    binding.libraryToggleDetails.setOnClickListener {
                        viewModel.toggleAddedToLibrary(game)
                        game.addedToLibrary = !game.addedToLibrary
                        binding.libraryToggleDetails.setImageResource(
                            if (game.addedToLibrary) R.drawable.baseline_check_24 else R.drawable.baseline_add_24
                        )
                    }
                }
            }
        }

        return binding.root
    }
}