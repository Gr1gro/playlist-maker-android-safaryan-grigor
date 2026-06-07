package com.practicum.myapplicationandroid

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicum.myapplicationandroid.ui.PlaylistMakerActivity
import com.practicum.myapplicationandroid.ui.playlists.PlaylistDetailsScreen

class PlaylistDetailsActivity : PlaylistMakerActivity() {

    private val playlistRepository by lazy {
        (application as PlaylistMakerApplication).playlistRepository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val playlistId = intent.getLongExtra(EXTRA_PLAYLIST_ID, -1L)
        if (playlistId == -1L) {
            finish()
            return
        }

        setPlaylistMakerContent {
            val playlist by playlistRepository.getPlaylist(playlistId)
                .collectAsStateWithLifecycle(initialValue = null)
            val tracks by playlistRepository.getPlaylistTracks(playlistId)
                .collectAsStateWithLifecycle(emptyList())

            playlist?.let { currentPlaylist ->
                PlaylistDetailsScreen(
                    modifier = Modifier.fillMaxSize(),
                    playlist = currentPlaylist,
                    tracks = tracks,
                    onBackClick = ::finish,
                )
            }
        }
    }

    companion object {
        const val EXTRA_PLAYLIST_ID = "extra_playlist_id"
    }
}
