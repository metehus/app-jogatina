package dev.matehus.appjogatina.data.game.repository

import dev.matehus.appjogatina.data.game.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    val allGames: Flow<List<Game>>
    suspend fun getGameById(id: Int): Flow<Game?>
    suspend fun upsert(game: Game)
    suspend fun toggleLibraryAdded(game: Game)
}