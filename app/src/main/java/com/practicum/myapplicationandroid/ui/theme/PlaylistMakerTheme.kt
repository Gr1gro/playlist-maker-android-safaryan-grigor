package com.practicum.myapplicationandroid.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class PlaylistMakerColors(
    val screenBackground: Color,
    val panelBackground: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val searchFieldBackground: Color,
    val searchFieldText: Color,
    val iconTint: Color,
)

val LocalPlaylistMakerColors = staticCompositionLocalOf {
    PlaylistMakerColors(
        screenBackground = YsWhite,
        panelBackground = YsWhite,
        primaryText = YsBlack,
        secondaryText = YsGray,
        searchFieldBackground = YsLightGray,
        searchFieldText = YsBlack,
        iconTint = YsBlack,
    )
}

private val LightPalette = PlaylistMakerColors(
    screenBackground = YsWhite,
    panelBackground = YsWhite,
    primaryText = YsBlack,
    secondaryText = YsGray,
    searchFieldBackground = YsLightGray,
    searchFieldText = YsBlack,
    iconTint = YsBlack,
)

private val DarkPalette = PlaylistMakerColors(
    screenBackground = YsBlack,
    panelBackground = YsBlack,
    primaryText = YsWhite,
    secondaryText = YsGray,
    searchFieldBackground = YsWhite,
    searchFieldText = YsBlack,
    iconTint = YsWhite,
)

private val LightColorScheme = lightColorScheme(
    primary = YsPrimary,
    onPrimary = YsWhite,
    background = YsLightGray,
    surface = YsWhite,
    onSurface = YsBlack,
)

private val DarkColorScheme = darkColorScheme(
    primary = YsPrimary,
    onPrimary = YsWhite,
    background = YsBlack,
    surface = YsBlack,
    onSurface = YsWhite,
)

@Composable
fun PlaylistMakerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val palette = if (darkTheme) DarkPalette else LightPalette
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(LocalPlaylistMakerColors provides palette) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}

@Composable
fun playlistMakerColors(): PlaylistMakerColors = LocalPlaylistMakerColors.current
