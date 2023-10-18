package dev.matehus.appjogatina.data.game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    val name: String,
    val imageUrl: String?
)
