package dev.matehus.appjogatina.data.game.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.data.game.dao.GameDao
import dev.matehus.appjogatina.data.game.dao.GameFirebaseDao
import dev.matehus.appjogatina.data.game.dao.GameRoomDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject
import kotlin.random.Random

class GameRepositoryImpl
     @Inject constructor (private val firebaseDao: GameFirebaseDao, private val roomDao: GameRoomDao) : GameRepository {

    override val allGames: Flow<List<Game>>
        get() = firebaseDao.listAll().catch {
            emitAll(roomDao.listAll())
        }

    override suspend fun getGameById(id: Int): Flow<Game?> {
        return firebaseDao.getGame(id).catch {
            emitAll(roomDao.getGame(id))
        }
    }

    override suspend fun upsert(game: Game) {
        if (game.id.isNullOrEmpty()) {
            roomDao.insert(game)
            firebaseDao.insert(game)
        } else {
            roomDao.update(game)
            firebaseDao.update(game)
        }
    }

    override suspend fun toggleLibraryAdded(game: Game) {
        val copy = game.clone()
        copy.addedToLibrary = !copy.addedToLibrary
        roomDao.update(game)
        firebaseDao.update(game)
    }
}