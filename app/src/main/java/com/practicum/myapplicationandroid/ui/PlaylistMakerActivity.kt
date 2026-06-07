package com.practicum.myapplicationandroid.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.practicum.myapplicationandroid.ui.theme.PlaylistMakerTheme

abstract class PlaylistMakerActivity : ComponentActivity() {

    protected fun setPlaylistMakerContent(content: @Composable () -> Unit) {
        enableEdgeToEdge()
        setContent {
            PlaylistMakerTheme(darkTheme = false) {
                content()
            }
        }
    }
}
