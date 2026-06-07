package com.practicum.myapplicationandroid

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.practicum.myapplicationandroid.ui.favorites.FAVORITES_SCREEN_TAG
import com.practicum.myapplicationandroid.ui.main.MENU_FAVORITES_TAG
import com.practicum.myapplicationandroid.ui.main.MENU_PLAYLISTS_TAG
import com.practicum.myapplicationandroid.ui.main.MENU_SEARCH_TAG
import com.practicum.myapplicationandroid.ui.main.MENU_SETTINGS_TAG
import com.practicum.myapplicationandroid.ui.playlists.PLAYLISTS_SCREEN_TAG
import com.practicum.myapplicationandroid.ui.search.SEARCH_BUTTON_TAG
import com.practicum.myapplicationandroid.ui.search.SEARCH_INITIAL_TAG
import com.practicum.myapplicationandroid.ui.search.SEARCH_INPUT_TAG
import com.practicum.myapplicationandroid.ui.search.SEARCH_SCREEN_TAG
import com.practicum.myapplicationandroid.ui.search.TRACKS_LIST_TAG
import com.practicum.myapplicationandroid.ui.settings.SETTINGS_SCREEN_TAG
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun searchMenuItem_opensSearchScreen() {
        composeTestRule.onNodeWithTag(MENU_SEARCH_TAG).performClick()
        composeTestRule.onNodeWithTag(SEARCH_SCREEN_TAG).assertIsDisplayed()
    }

    @Test
    fun settingsMenuItem_opensSettingsScreen() {
        composeTestRule.onNodeWithTag(MENU_SETTINGS_TAG).performClick()
        composeTestRule.onNodeWithTag(SETTINGS_SCREEN_TAG).assertIsDisplayed()
    }

    @Test
    fun playlistsMenuItem_launchesPlaylistsActivity() {
        composeTestRule.onNodeWithTag(MENU_PLAYLISTS_TAG).performClick()
        intended(hasComponent(PlaylistsActivity::class.java.name))
    }

    @Test
    fun favoritesMenuItem_launchesFavoritesActivity() {
        composeTestRule.onNodeWithTag(MENU_FAVORITES_TAG).performClick()
        intended(hasComponent(FavoritesActivity::class.java.name))
    }

    @Test
    fun searchScreen_showsTracksListAfterSearch() {
        composeTestRule.onNodeWithTag(MENU_SEARCH_TAG).performClick()
        composeTestRule.onNodeWithTag(SEARCH_SCREEN_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SEARCH_INITIAL_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SEARCH_INPUT_TAG).performTextInput("Кино")
        composeTestRule.onNodeWithTag(SEARCH_BUTTON_TAG).performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag(TRACKS_LIST_TAG)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(TRACKS_LIST_TAG).assertIsDisplayed()
    }

    @Test
    fun searchScreen_back_returnsToMain() {
        composeTestRule.onNodeWithTag(MENU_SEARCH_TAG).performClick()
        composeTestRule.onNodeWithTag(SEARCH_SCREEN_TAG).assertIsDisplayed()
        composeTestRule.activityRule.scenario.onActivity { activity ->
            activity.onBackPressedDispatcher.onBackPressed()
        }
        composeTestRule.onNodeWithTag(MENU_SEARCH_TAG).assertIsDisplayed()
    }
}
