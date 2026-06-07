package com.practicum.myapplicationandroid.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.ui.common.EmptyState
import com.practicum.myapplicationandroid.ui.common.ScreenTopBar
import com.practicum.myapplicationandroid.ui.common.TrackListItem
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

const val FAVORITES_SCREEN_TAG = "favorites_screen"

@Composable
fun FavoritesScreen(
    tracks: List<Track>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.screenBackground)
            .testTag(FAVORITES_SCREEN_TAG),
    ) {
        ScreenTopBar(
            titleRes = R.string.favorites_screen_title,
            onBackClick = onBackClick,
        )

        if (tracks.isEmpty()) {
            EmptyState(
                messageRes = R.string.favorites_empty,
                iconRes = R.drawable.ic_favorite_24,
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(tracks, key = { it.id }) { track ->
                    TrackListItem(track = track)
                }
            }
        }
    }
}
