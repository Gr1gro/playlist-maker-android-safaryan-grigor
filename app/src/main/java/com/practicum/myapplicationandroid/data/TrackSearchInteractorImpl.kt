package com.practicum.myapplicationandroid.data

import com.practicum.myapplicationandroid.domain.interactor.TrackSearchInteractor
import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.domain.repository.TracksRepository

class TrackSearchInteractorImpl(
    private val repository: TracksRepository,
) : TrackSearchInteractor {

    override suspend fun getAllTracks(): List<Track> {
        return repository.getAllTracks()
    }

    override suspend fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }
}
