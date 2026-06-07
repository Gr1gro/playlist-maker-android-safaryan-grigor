package com.practicum.myapplicationandroid.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors
import com.practicum.myapplicationandroid.util.formatTrackDuration

@Composable
fun TrackListItem(
    track: Track,
    modifier: Modifier = Modifier,
    showArrow: Boolean = true,
) {
    val colors = playlistMakerColors()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TrackArtwork(
            artworkUrl = track.artworkUrl,
            artworkResId = track.artworkResId,
            contentDescription = stringResource(R.string.track_cover, track.trackName),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
        ) {
            Text(
                text = track.trackName,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.primaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${track.artistName} • ${formatTrackDuration(track.trackTimeMillis)}",
                style = MaterialTheme.typography.bodyLarge,
                color = colors.secondaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (showArrow) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_forward_24),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = colors.secondaryText,
            )
        }
    }
}
