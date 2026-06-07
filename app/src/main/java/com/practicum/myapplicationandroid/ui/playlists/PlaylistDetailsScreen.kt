package com.practicum.myapplicationandroid.ui.playlists

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import coil.compose.AsyncImage
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.domain.model.Playlist
import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.ui.common.ScreenTopBar
import com.practicum.myapplicationandroid.ui.common.TrackListItem
import com.practicum.myapplicationandroid.ui.theme.YsLightGray
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors
import com.practicum.myapplicationandroid.util.formatTotalDuration

@Composable
fun PlaylistDetailsScreen(
    playlist: Playlist,
    tracks: List<Track>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()
    val context = LocalContext.current
    val totalDuration = tracks.sumOf { it.trackTimeMillis }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.screenBackground),
    ) {
        ScreenTopBar(onBackClick = onBackClick)

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(YsLightGray)
                .align(Alignment.CenterHorizontally),
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
                    modifier = Modifier.size(200.dp),
                    tint = colors.secondaryText,
                )
            }
        }

        Text(
            text = playlist.name,
            style = MaterialTheme.typography.titleMedium,
            color = colors.primaryText,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        if (playlist.description.isNotBlank()) {
            Text(
                text = playlist.description,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.secondaryText,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )
        }

        Text(
            text = stringResource(
                R.string.playlist_meta,
                formatTotalDuration(totalDuration),
                pluralStringResource(R.plurals.tracks_count, tracks.size, tracks.size),
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = colors.secondaryText,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        )

        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        ) {
            IconButton(
                onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            context.getString(R.string.share_playlist_message, playlist.name),
                        )
                    }
                    context.startActivity(Intent.createChooser(shareIntent, null))
                },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share_24),
                    contentDescription = stringResource(R.string.share_app),
                    tint = colors.iconTint,
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(tracks, key = { it.id }) { track ->
                TrackListItem(track = track)
            }
        }
    }
}
