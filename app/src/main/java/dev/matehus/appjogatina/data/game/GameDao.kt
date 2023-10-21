package dev.matehus.appjogatina.data.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("select * from games")
    fun listAll(): Flow<List<Game>>

    @Query("select * from games where addedToLibrary")
    fun listLibrary(): Flow<List<Game>>

    @Query("select * from games where id = :id")
    fun getGame(id: Int): Flow<Game>

    @Insert
    suspend fun insert(game: Game)

    @Update
    suspend fun update(game: Game)
}