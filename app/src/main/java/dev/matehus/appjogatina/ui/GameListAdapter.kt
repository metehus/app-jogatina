package dev.matehus.appjogatina.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.matehus.appjogatina.R
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.databinding.ListItemGameBinding
import dev.matehus.appjogatina.ui.details.GameDetailsViewModel
import dev.matehus.appjogatina.ui.home.HomeFragmentDirections
import dev.matehus.appjogatina.ui.home.HomeViewModel

class GameListAdapter(
    private val games: List<Game>,
    private val gameDetailsViewModel: GameDetailsViewModel
) : RecyclerView.Adapter<GameListAdapter.ViewHolder>() {
    init {
        Log.i("JGTN", "Games list: $games")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = games[position]

        Log.d("JGTN", "p: $position, i: $item")

        holder.txtNome.text = item.name
        holder.imgFoto.load(item.imageUrl) {
            crossfade(true)
        }
        holder.itemRating.rating = item.rating ?: 0f


        holder.gameStatusText.text = item.status.toString()
        holder.gameStatusText.setBackgroundColor(
            holder.gameStatusText.context.resources.getColor(
                item.status.colorId
            )
        )
        Log.d("JGTN", "lib: ${item.addedToLibrary}")
        holder.libraryToggle.setImageResource(
            if (item.addedToLibrary) R.drawable.baseline_check_24 else R.drawable.baseline_add_24
        )

        holder.libraryToggle.setOnClickListener {
            gameDetailsViewModel.toggleAddedToLibrary(item)
        }

        holder.itemRoot.setOnClickListener {
            gameDetailsViewModel.setGame(item.clone())
            val action = HomeFragmentDirections.actionHomeOpenGameDetails()
            it.findNavController().navigate(action)
        }
    }

    inner class ViewHolder(binding: ListItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFoto = binding.imageView
        val txtNome = binding.gameName
        val gameStatusText = binding.gameStatus
        val libraryToggle = binding.libraryToggle
        val itemRating = binding.ratingBar
        val itemRoot = binding.root
    }
}