package com.practicum.myapplicationandroid.domain.interactor

import com.practicum.myapplicationandroid.domain.model.Track

interface TrackSearchInteractor {
    suspend fun getAllTracks(): List<Track>

    suspend fun searchTracks(expression: String): List<Track>
}
