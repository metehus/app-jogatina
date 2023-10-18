package dev.matehus.appjogatina.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.databinding.ListItemGameBinding

class HomeGameListAdapter(
    private val games: List<Game>,
    val viewModel: HomeViewModel
) : RecyclerView.Adapter<HomeGameListAdapter.ViewHolder>() {
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
    }

    inner class ViewHolder(binding: ListItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFoto = binding.imageView
        val txtNome = binding.gameName
    }
}