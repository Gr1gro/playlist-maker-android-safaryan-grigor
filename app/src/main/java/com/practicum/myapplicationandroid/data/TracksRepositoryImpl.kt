package com.practicum.myapplicationandroid.data

import com.practicum.myapplicationandroid.data.network.RetrofitNetworkClient
import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.domain.repository.TracksRepository

class TracksRepositoryImpl(
    private val networkClient: RetrofitNetworkClient,
) : TracksRepository {

    override suspend fun getAllTracks(): List<Track> {
        return networkClient.getAllTracks()
    }

    override suspend fun searchTracks(expression: String): List<Track> {
        return networkClient.searchTracks(expression)
    }
}
