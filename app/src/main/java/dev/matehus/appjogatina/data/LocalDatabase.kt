package dev.matehus.appjogatina.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.matehus.appjogatina.data.game.Game
import dev.matehus.appjogatina.data.game.GameDao

@Database(entities = [Game::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object{

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun get(context: Context): LocalDatabase {
            if (INSTANCE == null) {

                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "local_db.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}