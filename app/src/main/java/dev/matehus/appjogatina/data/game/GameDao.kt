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
    @Insert
    suspend fun insert(game: Game)
    @Update
    suspend fun update(game: Game)

}