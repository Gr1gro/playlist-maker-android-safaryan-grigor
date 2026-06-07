package com.practicum.myapplicationandroid.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.practicum.myapplicationandroid.domain.model.Playlist
import com.practicum.myapplicationandroid.domain.model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

private val Context.playlistDataStore: DataStore<Preferences> by preferencesDataStore(name = "playlists_lana")

class PlaylistRepository(private val context: Context) {

    private val allTracks: List<Track> = defaultTracks()

    val playlists: Flow<List<Playlist>> = context.playlistDataStore.data.map { preferences ->
        parsePlaylists(preferences[PLAYLISTS_JSON_KEY]).sortedBy { it.id }
    }

    val favoriteTracks: Flow<List<Track>> = context.playlistDataStore.data.map { preferences ->
        parseIds(preferences[FAVORITES_JSON_KEY])
            .mapNotNull { id -> allTracks.find { it.id == id } }
    }

    fun getPlaylist(playlistId: Long): Flow<Playlist?> = playlists.map { list ->
        list.find { it.id == playlistId }
    }

    fun getPlaylistTracks(playlistId: Long): Flow<List<Track>> = playlists.map { list ->
        val playlist = list.find { it.id == playlistId } ?: return@map emptyList()
        playlist.trackIds.mapNotNull { id -> allTracks.find { it.id == id } }
    }

    suspend fun ensureDefaultData() {
        context.playlistDataStore.edit { preferences ->
            if (preferences[INITIALIZED_KEY] == true) return@edit
            preferences[INITIALIZED_KEY] = true
            preferences[PLAYLISTS_JSON_KEY] = JSONArray(defaultPlaylists().map { it.toJson() }).toString()
            preferences[FAVORITES_JSON_KEY] = JSONArray(defaultFavoriteIds()).toString()
            preferences[NEXT_PLAYLIST_ID_KEY] = 5L
        }
    }

    suspend fun createPlaylist(name: String, description: String): Long {
        var newId = 0L
        context.playlistDataStore.edit { preferences ->
            newId = preferences[NEXT_PLAYLIST_ID_KEY] ?: 1L
            val current = parsePlaylists(preferences[PLAYLISTS_JSON_KEY]).toMutableList()
            current.add(
                Playlist(
                    id = newId,
                    name = name.trim(),
                    description = description.trim(),
                ),
            )
            preferences[PLAYLISTS_JSON_KEY] = JSONArray(current.map { it.toJson() }).toString()
            preferences[NEXT_PLAYLIST_ID_KEY] = newId + 1
        }
        return newId
    }

    private fun defaultTracks(): List<Track> = MockTracks.lanaTracks

    private fun defaultPlaylists(): List<Playlist> = listOf(
        Playlist(
            id = 1,
            name = "Lana Del Rey Essentials",
            description = "Main playlist",
            coverUri = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=600&auto=format&fit=crop&q=80&aspect_ratio=1:1",
            trackIds = (1L..10L).toList(),
        ),
        Playlist(
            id = 2,
            name = "Born To Die Era",
            coverUri = "https://images.unsplash.com/photo-1519681393784-d120267933ba?w=600&auto=format&fit=crop&q=80&aspect_ratio=1:1",
            trackIds = listOf(3, 4, 6, 11, 12),
        ),
        Playlist(
            id = 3,
            name = "Sad Girl Summer",
            coverUri = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&auto=format&fit=crop&q=80&aspect_ratio=1:1",
            trackIds = listOf(1, 2, 5, 8, 9, 13),
        ),
        Playlist(
            id = 4,
            name = "Night Drive",
            coverUri = "https://images.unsplash.com/photo-1493246507139-91e8fad9978e?w=600&auto=format&fit=crop&q=80&aspect_ratio=1:1",
            trackIds = listOf(7, 10, 14, 15),
        ),
    )

    private fun defaultFavoriteIds(): List<Long> = listOf(1, 2, 5, 8, 10, 13)

    private fun parsePlaylists(json: String?): List<Playlist> {
        if (json.isNullOrBlank()) return emptyList()
        val array = JSONArray(json)
        return buildList {
            for (index in 0 until array.length()) {
                add(array.getJSONObject(index).toPlaylist())
            }
        }
    }

    private fun parseIds(json: String?): List<Long> {
        if (json.isNullOrBlank()) return emptyList()
        val array = JSONArray(json)
        return buildList {
            for (index in 0 until array.length()) {
                add(array.getLong(index))
            }
        }
    }

    private fun Playlist.toJson(): JSONObject = JSONObject().apply {
        put("id", id)
        put("name", name)
        put("description", description)
        put("coverUri", coverUri)
        put("trackIds", JSONArray(trackIds))
    }

    private fun JSONObject.toPlaylist(): Playlist = Playlist(
        id = getLong("id"),
        name = getString("name"),
        description = optString("description", ""),
        coverUri = optString("coverUri").takeIf { it.isNotBlank() },
        trackIds = buildList {
            val tracksArray = optJSONArray("trackIds") ?: return@buildList
            for (index in 0 until tracksArray.length()) {
                add(tracksArray.getLong(index))
            }
        },
    )

    private companion object {
        val INITIALIZED_KEY = booleanPreferencesKey("initialized")
        val PLAYLISTS_JSON_KEY = stringPreferencesKey("playlists_json")
        val FAVORITES_JSON_KEY = stringPreferencesKey("favorites_json")
        val NEXT_PLAYLIST_ID_KEY = longPreferencesKey("next_playlist_id")
    }
}
