package dev.matehus.appjogatina

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.matehus.appjogatina.data.LocalDatabase
import dev.matehus.appjogatina.data.game.GameDao
import dev.matehus.appjogatina.data.game.GameRepository
import dev.matehus.appjogatina.data.game.GameRepositoryLocal
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppJogatina: Application() {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext ctx: Context) = LocalDatabase.get(ctx)

    @Provides
    fun provideGameDao(database: LocalDatabase) = database.gameDao()

    @Provides
    fun provideGameRepository(gameDao: GameDao): GameRepository = GameRepositoryLocal(gameDao)
}