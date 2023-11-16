package dev.matehus.appjogatina.data.game.dao

import dev.matehus.appjogatina.data.game.Game
import kotlinx.coroutines.flow.Flow

interface GameDao {
    fun listAll(): Flow<List<Game>>
    suspend fun insert(game: Game)
    suspend fun update(game: Game)
}