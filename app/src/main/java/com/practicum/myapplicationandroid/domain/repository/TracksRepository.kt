package com.practicum.myapplicationandroid.domain.repository

import com.practicum.myapplicationandroid.domain.model.Track

interface TracksRepository {
    suspend fun getAllTracks(): List<Track>

    suspend fun searchTracks(expression: String): List<Track>
}
