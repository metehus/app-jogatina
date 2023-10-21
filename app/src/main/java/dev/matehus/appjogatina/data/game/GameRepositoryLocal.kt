package dev.matehus.appjogatina.data.game

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryLocal
@Inject constructor(val gameDao: GameDao) : GameRepository {
    override val allGames: Flow<List<Game>>
        get() = gameDao.listAll()

    override val libraryGames: Flow<List<Game>>
        get() = gameDao.listLibrary()

    override suspend fun getGameById(id: Int): Flow<Game> = gameDao.getGame(id)

    override suspend fun upsert(game: Game) {
        if (game.id != null) gameDao.update(game)
        else gameDao.insert(game)
    }

    override suspend fun toggleLibraryAdded(game: Game) {
        val copy = game.clone()
        copy.addedToLibrary = !copy.addedToLibrary
        gameDao.update(copy)
    }
}