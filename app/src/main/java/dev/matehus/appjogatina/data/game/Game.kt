package dev.matehus.appjogatina.data.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.matehus.appjogatina.R

enum class GameStatus (val colorId: Int) {
    NotPlayed(R.color.colorNotPlayed),
    Interested(R.color.colorInterested),
    Played(R.color.colorPlayed)
}

@Entity(tableName = "games")
data class Game(
    @PrimaryKey
    val id: Int?,
    var docId: String?,
    val name: String,
    val imageUrl: String?,
    var addedToLibrary: Boolean,
    var status: GameStatus,
    var rating: Float?
) {
    constructor(name: String, imageUrl: String?) : this(
        null,
        null,
        name,
        imageUrl,
        false,
        GameStatus.NotPlayed,
        null
    )

    constructor() : this(
        null,
        null,
        "default",
        null,
        false,
        GameStatus.NotPlayed,
        null
    )

    fun clone(): Game {
        return Game(
            this.id,
            this.docId,
            this.name,
            this.imageUrl,
            this.addedToLibrary,
            this.status,
            this.rating
        )
    }
}
