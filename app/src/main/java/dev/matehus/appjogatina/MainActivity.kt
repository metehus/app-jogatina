package dev.matehus.appjogatina

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.matehus.appjogatina.data.game.GameRepository
import dev.matehus.appjogatina.data.game.sampleGameList
import dev.matehus.appjogatina.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var gameRepository: GameRepository

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

        Log.i("JGTN", "Carregando jogos")
        lifecycleScope.launch {
            loadGames()
        }
    }

    suspend fun loadGames() {
        Log.i("JGTN", "Iniciando carregamento")
        gameRepository.allGames.collect {
            Log.i("JGTN", "Games size: ${it.size}")
            if (it.size === 0) {
                for (game in sampleGameList) {
                    Log.i("JGTN", "Game: ${game}")
                    gameRepository.upsert(game)
                }
            }
        }
    }
}