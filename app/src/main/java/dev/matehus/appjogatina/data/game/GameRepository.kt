package dev.matehus.appjogatina.data.game

import kotlinx.coroutines.flow.Flow

interface GameRepository {
    val allGames: Flow<List<Game>>
    val libraryGames: Flow<List<Game>>
    suspend fun getGameById(id: Int): Flow<Game>
    suspend fun upsert(game: Game)
    suspend fun toggleLibraryAdded(game: Game)
}