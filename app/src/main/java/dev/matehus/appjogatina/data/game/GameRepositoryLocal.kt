package dev.matehus.appjogatina.data.game

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryLocal
    @Inject constructor(val gameDao: GameDao): GameRepository {
    override val allGames: Flow<List<Game>>
        get() = gameDao.listAll()

    override suspend fun upsert(game: Game) {
        if (game.id != null)  gameDao.update(game)
        else gameDao.insert(game)
    }
}