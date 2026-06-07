package com.practicum.myapplicationandroid

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.practicum.myapplicationandroid.ui.PlaylistMakerActivity
import com.practicum.myapplicationandroid.ui.playlists.PlaylistsScreen
import kotlinx.coroutines.launch

class PlaylistsActivity : PlaylistMakerActivity() {

    private val playlistRepository by lazy {
        (application as PlaylistMakerApplication).playlistRepository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            playlistRepository.ensureDefaultData()
        }
        setPlaylistMakerContent {
            val playlists by playlistRepository.playlists.collectAsStateWithLifecycle(emptyList())

            PlaylistsScreen(
                modifier = Modifier.fillMaxSize(),
                playlists = playlists,
                onBackClick = ::finish,
                onPlaylistClick = ::openPlaylistDetails,
                onCreatePlaylistClick = ::openNewPlaylist,
            )
        }
    }

    private fun openPlaylistDetails(playlistId: Long) {
        startActivity(
            Intent(this, PlaylistDetailsActivity::class.java).apply {
                putExtra(PlaylistDetailsActivity.EXTRA_PLAYLIST_ID, playlistId)
            },
        )
    }

    private fun openNewPlaylist() {
        startActivity(Intent(this, NewPlaylistActivity::class.java))
    }
}
