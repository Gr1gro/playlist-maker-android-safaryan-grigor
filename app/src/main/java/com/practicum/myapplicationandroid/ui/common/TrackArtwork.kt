package com.practicum.myapplicationandroid.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.theme.YsLightGray

@Composable
fun TrackArtwork(
    artworkUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    @DrawableRes artworkResId: Int? = null,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(YsLightGray),
        contentAlignment = Alignment.Center,
    ) {
        when {
            artworkResId != null -> {
                Image(
                    painter = painterResource(artworkResId),
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }

            !artworkUrl.isNullOrBlank() -> {
                AsyncImage(
                    model = artworkUrl,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_music),
                    error = painterResource(R.drawable.ic_music),
                )
            }

            else -> {
                Icon(
                    painter = painterResource(R.drawable.ic_music),
                    contentDescription = contentDescription,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}
