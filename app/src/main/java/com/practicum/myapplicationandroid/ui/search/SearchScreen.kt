package com.practicum.myapplicationandroid.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.myapplicationandroid.R
import com.practicum.myapplicationandroid.ui.common.ScreenTopBar
import com.practicum.myapplicationandroid.ui.common.TrackListItem
import com.practicum.myapplicationandroid.ui.theme.PlaylistMakerTheme
import com.practicum.myapplicationandroid.ui.theme.playlistMakerColors

const val SEARCH_SCREEN_TAG = "search_screen"
const val SEARCH_INPUT_TAG = "search_input"
const val SEARCH_BUTTON_TAG = "search_button"
const val TRACKS_LIST_TAG = "tracks_list"
const val TRACKS_LOADING_TAG = "tracks_loading"
const val TRACKS_ERROR_TAG = "tracks_error"
const val SEARCH_INITIAL_TAG = "search_initial"

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.getViewModelFactory()),
) {
    var query by rememberSaveable { mutableStateOf("") }
    val screenState by viewModel.searchScreenState.collectAsStateWithLifecycle()
    val colors = playlistMakerColors()

    fun performSearch() {
        viewModel.search(query.trim())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.screenBackground)
            .testTag(SEARCH_SCREEN_TAG),
    ) {
        ScreenTopBar(
            titleRes = R.string.search_screen_title,
            onBackClick = onBackClick,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.searchFieldBackground),
            contentAlignment = Alignment.CenterStart,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { performSearch() },
                    modifier = Modifier
                        .size(36.dp)
                        .testTag(SEARCH_BUTTON_TAG),
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search),
                        tint = colors.iconTint,
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_hint),
                            color = colors.secondaryText,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }

                    BasicTextField(
                        value = query,
                        onValueChange = { query = it },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = colors.searchFieldText,
                        ),
                        singleLine = true,
                        cursorBrush = SolidColor(colors.searchFieldText),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = { performSearch() }),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(SEARCH_INPUT_TAG),
                    )
                }

                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            query = ""
                            viewModel.resetState()
                        },
                        modifier = Modifier.size(36.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.clear_search),
                            tint = colors.iconTint,
                        )
                    }
                }
            }
        }

        SearchResultsContent(
            screenState = screenState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun SearchResultsContent(
    screenState: SearchState,
    modifier: Modifier = Modifier,
) {
    when (screenState) {
        is SearchState.Initial -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .testTag(SEARCH_INITIAL_TAG),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.search_initial_hint),
                    style = MaterialTheme.typography.bodyLarge,
                    color = playlistMakerColors().secondaryText,
                )
            }
        }

        is SearchState.Searching -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .testTag(TRACKS_LOADING_TAG),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        is SearchState.Success -> {
            val tracks = screenState.list
            if (tracks.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.search_nothing_found),
                        style = MaterialTheme.typography.bodyLarge,
                        color = playlistMakerColors().secondaryText,
                    )
                }
            } else {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .testTag(TRACKS_LIST_TAG),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(tracks, key = { it.id }) { track ->
                        TrackListItem(track = track)
                        HorizontalDivider(thickness = 0.5.dp)
                    }
                }
            }
        }

        is SearchState.Fail -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .testTag(TRACKS_ERROR_TAG),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.search_error, screenState.error),
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    PlaylistMakerTheme {
        SearchScreen(onBackClick = {})
    }
}
