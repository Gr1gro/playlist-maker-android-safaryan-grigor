package com.practicum.myapplicationandroid.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.ui.common.TrackArtwork
import com.practicum.myapplicationandroid.ui.theme.PlaylistMakerTheme
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors
import com.practicum.myapplicationandroid.util.formatTrackDuration
import com.practicum.myapplicationandroid.util.parseTrackTimeToMillis

@Composable
fun SearchTrackListItem(
    track: Track,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()

    Row(
        modifier = modifier.fillMaxWidth(),
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
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = track.trackName,
                fontWeight = FontWeight.Bold,
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
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchTrackListItem() {
    PlaylistMakerTheme {
        SearchTrackListItem(
            track = Track(
                id = 1,
                trackName = "Владивосток 2000",
                artistName = "Мумий Тролль",
                trackTimeMillis = parseTrackTimeToMillis("2:38"),
                artworkResId = R.drawable.cover_vladivostok,
            ),
        )
    }
}
