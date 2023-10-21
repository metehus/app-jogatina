package dev.matehus.appjogatina.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.data.game.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject constructor(val repository: GameRepository): ViewModel() {

    private var _games = MutableStateFlow(listOf<Game>())
    val games : Flow<List<Game>> = _games


    init {
        viewModelScope.launch {
            repository.allGames.collect {
                Log.d("JGTN", "l: $it")
                _games.value = it

            }
        }
    }
}