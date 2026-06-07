package com.practicum.myapplicationandroid.ui.playlists

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.common.ScreenTopBar
import com.practicum.myapplicationandroid.ui.theme.YsGray
import com.practicum.myapplicationandroid.ui.theme.YsLightGray
import com.practicum.myapplicationandroid.ui.theme.YsPrimary
import com.practicum.myapplicationandroid.ui.theme.YsWhite
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

@Composable
fun NewPlaylistScreen(
    onBackClick: () -> Unit,
    onCreateClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val colors = playlistMakerColors()
    val isCreateEnabled = name.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.screenBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScreenTopBar(
            titleRes = R.string.new_playlist_title,
            onBackClick = onBackClick,
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .size(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, YsGray, RoundedCornerShape(8.dp))
                .background(YsLightGray),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add_24),
                contentDescription = stringResource(R.string.add_cover),
                modifier = Modifier.size(48.dp),
                tint = colors.secondaryText,
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.playlist_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            singleLine = true,
            colors = outlinedFieldColors(colors.primaryText),
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(R.string.playlist_description)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = outlinedFieldColors(colors.primaryText),
        )

        Button(
            onClick = { onCreateClick(name, description) },
            enabled = isCreateEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = YsPrimary,
                contentColor = YsWhite,
                disabledContainerColor = YsGray,
                disabledContentColor = YsWhite,
            ),
        ) {
            Text(
                text = stringResource(R.string.create),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun outlinedFieldColors(textColor: androidx.compose.ui.graphics.Color) = OutlinedTextFieldDefaults.colors(
    focusedTextColor = textColor,
    unfocusedTextColor = textColor,
    focusedBorderColor = YsPrimary,
    unfocusedBorderColor = YsGray,
    focusedLabelColor = YsPrimary,
    unfocusedLabelColor = YsGray,
    cursorColor = YsPrimary,
)
