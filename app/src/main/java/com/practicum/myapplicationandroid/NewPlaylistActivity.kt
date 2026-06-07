package com.practicum.myapplicationandroid

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.practicum.myapplicationandroid.ui.PlaylistMakerActivity
import com.practicum.myapplicationandroid.ui.playlists.NewPlaylistScreen
import kotlinx.coroutines.launch

class NewPlaylistActivity : PlaylistMakerActivity() {

    private val playlistRepository by lazy {
        (application as PlaylistMakerApplication).playlistRepository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPlaylistMakerContent {
            NewPlaylistScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = ::finish,
                onCreateClick = ::createPlaylist,
            )
        }
    }

    private fun createPlaylist(name: String, description: String) {
        lifecycleScope.launch {
            playlistRepository.createPlaylist(name, description)
            finish()
        }
    }
}
