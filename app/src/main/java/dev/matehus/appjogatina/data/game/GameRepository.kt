package dev.matehus.appjogatina.data.game

import kotlinx.coroutines.flow.Flow

interface GameRepository {
    val allGames: Flow<List<Game>>
    suspend fun upsert(game: Game)
}