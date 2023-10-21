package dev.matehus.appjogatina.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.data.game.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel
@Inject constructor(val gameRepository: GameRepository) : ViewModel() {
    private val _game = MutableStateFlow<Game?>(null)
    val game = _game.asStateFlow()

    fun setGame(game: Game) {
        _game.value = game
    }

    fun update(game: Game){
        viewModelScope.launch {
            gameRepository.upsert(game)
        }
    }

    fun toggleAddedToLibrary(game: Game) {
        viewModelScope.launch {
            gameRepository.toggleLibraryAdded(game)
        }
    }
}