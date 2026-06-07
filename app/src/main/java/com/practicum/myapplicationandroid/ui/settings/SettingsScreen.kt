package com.practicum.myapplicationandroid.ui.settings

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.common.ScreenTopBar
import com.practicum.myapplicationandroid.ui.theme.PlaylistMakerTheme
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

const val SETTINGS_SCREEN_TAG = "settings_screen"

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onSupportClick: () -> Unit,
    onUserAgreementClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = playlistMakerColors()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.screenBackground)
            .testTag(SETTINGS_SCREEN_TAG),
    ) {
        ScreenTopBar(
            titleRes = R.string.settings_screen_title,
            onBackClick = onBackClick,
        )

        SettingsRow(
            titleRes = R.string.share_app,
            iconRes = R.drawable.ic_share_24,
            iconTint = colors.iconTint,
            onClick = onShareClick,
        )
        SettingsRow(
            titleRes = R.string.write_to_developers,
            iconRes = R.drawable.ic_support_24,
            iconTint = colors.iconTint,
            onClick = onSupportClick,
        )
        SettingsRow(
            titleRes = R.string.user_agreement,
            iconRes = R.drawable.ic_arrow_forward_24,
            iconTint = colors.iconTint,
            onClick = onUserAgreementClick,
        )
    }
}

@Composable
private fun SettingsRow(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int? = null,
    iconTint: androidx.compose.ui.graphics.Color = playlistMakerColors().iconTint,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    val colors = playlistMakerColors()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClick,
                    )
                } else {
                    Modifier
                },
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.bodyLarge,
            color = colors.primaryText,
            modifier = Modifier.weight(1f),
        )
        when {
            trailing != null -> trailing()
            iconRes != null -> Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = iconTint,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    PlaylistMakerTheme {
        SettingsScreen(
            onBackClick = {},
            onShareClick = {},
            onSupportClick = {},
            onUserAgreementClick = {},
        )
    }
}
