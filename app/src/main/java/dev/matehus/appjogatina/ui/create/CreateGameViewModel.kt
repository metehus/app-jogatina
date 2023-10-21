package dev.matehus.appjogatina.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.data.game.GameRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel
@Inject constructor(private val repository: GameRepository) : ViewModel() {
    fun create(game: Game) {
        viewModelScope.launch {
            repository.upsert(game)
        }
    }
}