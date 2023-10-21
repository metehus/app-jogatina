package dev.matehus.appjogatina.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.matehus.appjogatina.databinding.FragmentHomeBinding
import dev.matehus.appjogatina.ui.GameListAdapter
import dev.matehus.appjogatina.ui.details.GameDetailsViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel by activityViewModels<HomeViewModel>()
        val gameDetailsViewModel by activityViewModels<GameDetailsViewModel>()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.floatingActionButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToCreateGameFragment()
            findNavController().navigate(action)
        }


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.games.collect { Log.i("JGTN", "asdasdasdasdasda, a: ${it.size} $it")
                    binding.gamesList.adapter = GameListAdapter(it, gameDetailsViewModel)
                    binding.gamesList.layoutManager = LinearLayoutManager(context)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}