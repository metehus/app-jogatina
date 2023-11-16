package dev.matehus.appjogatina.data.game.dao

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import dev.matehus.appjogatina.data.game.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.random.Random

class GameFirebaseDao : GameDao {
    private val firestore = Firebase.firestore

    private val gamesCollection = firestore.collection("games")
    override fun listAll(): Flow<List<Game>> {
        return gamesCollection.snapshots().map {
            return@map it.documents.mapNotNull { doc ->
                val game = doc.toObject<Game>()
                game?.id = doc.id
                game
            }
        }
    }

    fun getGame(id: Int): Flow<Game?> {
        return gamesCollection.document(id.toString()).snapshots().map {
            it.toObject<Game>()
        }
    }

    override suspend fun insert(game: Game) {
        gamesCollection.document(game.id.toString()!!).set(game)
    }

    override suspend fun update(game: Game) {
        gamesCollection.document(game.id.toString()!!).set(game)
    }
}