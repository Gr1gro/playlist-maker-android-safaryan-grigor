package com.practicum.myapplicationandroid.data

import com.practicum.myapplicationandroid.domain.model.Track

class Storage {

    fun getAllTracks(): List<Track> = MockTracks.allTracks

    fun searchTracks(expression: String): List<Track> {
        if (expression.isBlank()) return emptyList()
        return MockTracks.allTracks.filter { track ->
            track.trackName.contains(expression, ignoreCase = true) ||
                track.artistName.contains(expression, ignoreCase = true)
        }
    }
}
