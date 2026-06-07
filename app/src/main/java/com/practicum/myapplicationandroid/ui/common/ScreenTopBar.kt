package com.practicum.myapplicationandroid.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

@Composable
fun ScreenTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
) {
    val colors = playlistMakerColors()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 4.dp, top = 10.dp, bottom = 12.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(48.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back_24),
                contentDescription = stringResource(R.string.back),
                tint = colors.iconTint,
            )
        }
        if (titleRes != null) {
            Text(
                text = stringResource(titleRes),
                style = MaterialTheme.typography.titleMedium,
                color = colors.primaryText,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
    }
}
