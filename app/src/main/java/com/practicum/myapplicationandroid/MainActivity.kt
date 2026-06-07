package com.practicum.myapplicationandroid

import android.content.Intent
import android.os.Bundle
import com.practicum.myapplicationandroid.navigation.PlaylistHost
import com.practicum.myapplicationandroid.ui.PlaylistMakerActivity

class MainActivity : PlaylistMakerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPlaylistMakerContent {
            PlaylistHost(
                onPlaylistsClick = ::openPlaylistsScreen,
                onFavoritesClick = ::openFavoritesScreen,
            )
        }
    }

    private fun openPlaylistsScreen() {
        startActivity(Intent(this, PlaylistsActivity::class.java))
    }

    private fun openFavoritesScreen() {
        startActivity(Intent(this, FavoritesActivity::class.java))
    }
}
