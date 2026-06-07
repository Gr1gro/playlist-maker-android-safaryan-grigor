package com.practicum.myapplicationandroid

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.practicum.myapplicationandroid.ui.PlaylistMakerActivity
import com.practicum.myapplicationandroid.ui.favorites.FavoritesScreen
import kotlinx.coroutines.launch

class FavoritesActivity : PlaylistMakerActivity() {

    private val playlistRepository by lazy {
        (application as PlaylistMakerApplication).playlistRepository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            playlistRepository.ensureDefaultData()
        }

        setPlaylistMakerContent {
            val tracks by playlistRepository.favoriteTracks.collectAsStateWithLifecycle(emptyList())

            FavoritesScreen(
                modifier = Modifier.fillMaxSize(),
                tracks = tracks,
                onBackClick = ::finish,
            )
        }
    }
}
