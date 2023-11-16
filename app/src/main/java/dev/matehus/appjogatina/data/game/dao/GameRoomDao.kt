package dev.matehus.appjogatina.data.game.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.matehus.appjogatina.data.game.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameRoomDao: GameDao {
    @Query("select * from games")
    override fun listAll(): Flow<List<Game>>

    @Query("select * from games where addedToLibrary")
    fun listLibrary(): Flow<List<Game>>

    @Query("select * from games where id = :id")
    fun getGame(id: Int): Flow<Game?>

    @Insert
    override suspend fun insert(game: Game)

    @Update
    override suspend fun update(game: Game)
}