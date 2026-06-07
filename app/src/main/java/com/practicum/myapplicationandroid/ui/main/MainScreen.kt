package com.practicum.myapplicationandroid.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.theme.PlaylistMakerTheme
import com.practicum.myapplicationandroid.ui.theme.YsPrimary
import com.practicum.myapplicationandroid.ui.theme.YsWhite
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

const val MENU_SEARCH_TAG = "menu_search"
const val MENU_PLAYLISTS_TAG = "menu_playlists"
const val MENU_FAVORITES_TAG = "menu_favorites"
const val MENU_SETTINGS_TAG = "menu_settings"

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onPlaylistsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(YsPrimary),
    ) {
        Text(
            text = stringResource(R.string.main_title),
            style = MaterialTheme.typography.titleMedium,
            color = YsWhite,
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = 16.dp, top = 14.dp, bottom = 12.dp),
        )

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            color = colors.panelBackground,
        ) {
            Column {
                MainMenuItem(
                    iconRes = R.drawable.ic_search_24,
                    titleRes = R.string.search,
                    onClick = onSearchClick,
                    testTag = MENU_SEARCH_TAG,
                )
                MainMenuItem(
                    iconRes = R.drawable.ic_library_24,
                    titleRes = R.string.playlists,
                    onClick = onPlaylistsClick,
                    testTag = MENU_PLAYLISTS_TAG,
                )
                MainMenuItem(
                    iconRes = R.drawable.ic_favorite_24,
                    titleRes = R.string.favorites,
                    onClick = onFavoritesClick,
                    testTag = MENU_FAVORITES_TAG,
                )
                MainMenuItem(
                    iconRes = R.drawable.ic_settings_24,
                    titleRes = R.string.settings,
                    onClick = onSettingsClick,
                    testTag = MENU_SETTINGS_TAG,
                )
            }
        }
    }
}

@Composable
private fun MainMenuItem(
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String? = null,
) {
    val colors = playlistMakerColors()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp)
            .then(if (testTag != null) Modifier.testTag(testTag) else Modifier)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colors.iconTint,
        )
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.bodyLarge,
            color = colors.primaryText,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
        )
        Icon(
            painter = painterResource(R.drawable.ic_arrow_forward_24),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colors.secondaryText,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    PlaylistMakerTheme {
        MainScreen(
            onSearchClick = {},
            onPlaylistsClick = {},
            onFavoritesClick = {},
            onSettingsClick = {},
        )
    }
}
