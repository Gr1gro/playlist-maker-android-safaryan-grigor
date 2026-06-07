package com.practicum.myapplicationandroid.ui.playlists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import coil.compose.AsyncImage
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.domain.model.Playlist
import com.practicum.myapplicationandroid.ui.common.EmptyState
import com.practicum.myapplicationandroid.ui.common.ScreenTopBar
import com.practicum.myapplicationandroid.ui.theme.YsLightGray
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

const val PLAYLISTS_SCREEN_TAG = "playlists_screen"
const val PLAYLISTS_FAB_TAG = "playlists_fab"

@Composable
fun PlaylistsScreen(
    playlists: List<Playlist>,
    onBackClick: () -> Unit,
    onPlaylistClick: (Long) -> Unit,
    onCreatePlaylistClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag(PLAYLISTS_SCREEN_TAG),
        containerColor = colors.screenBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreatePlaylistClick,
                modifier = Modifier.testTag(PLAYLISTS_FAB_TAG),
                containerColor = YsLightGray,
                contentColor = colors.iconTint,
                shape = CircleShape,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add_24),
                    contentDescription = stringResource(R.string.create_playlist),
                    tint = colors.iconTint,
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            ScreenTopBar(
                titleRes = R.string.playlists_screen_title,
                onBackClick = onBackClick,
            )

            if (playlists.isEmpty()) {
                EmptyState(
                    messageRes = R.string.playlists_empty,
                    iconRes = R.drawable.ic_library_24,
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(playlists, key = { it.id }) { playlist ->
                        PlaylistListItem(
                            playlist = playlist,
                            onClick = { onPlaylistClick(playlist.id) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaylistListItem(
    playlist: Playlist,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(YsLightGray),
            contentAlignment = Alignment.Center,
        ) {
            if (!playlist.coverUri.isNullOrBlank()) {
                AsyncImage(
                    model = playlist.coverUri,
                    contentDescription = playlist.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_placeholder_cover),
                    error = painterResource(R.drawable.ic_placeholder_cover),
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_placeholder_cover),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = colors.secondaryText,
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
        ) {
            Text(
                text = playlist.name,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.primaryText,
            )
            Text(
                text = pluralStringResource(
                    R.plurals.tracks_count,
                    playlist.trackIds.size,
                    playlist.trackIds.size,
                ),
                style = MaterialTheme.typography.bodyLarge,
                color = colors.secondaryText,
            )
        }
    }
}
