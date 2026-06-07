package com.practicum.myapplicationandroid.data.network

import com.practicum.myapplicationandroid.data.Storage
import com.practicum.myapplicationandroid.domain.model.Track
import kotlinx.coroutines.delay
import java.io.IOException

class RetrofitNetworkClient(
    private val storage: Storage,
) {

    suspend fun searchTracks(expression: String): List<Track> {
        delay(SEARCH_DELAY_MS)
        if (SIMULATE_NETWORK_ERROR) {
            throw IOException("Ошибка сети")
        }
        return storage.searchTracks(expression)
    }

    suspend fun getAllTracks(): List<Track> {
        delay(SEARCH_DELAY_MS)
        if (SIMULATE_NETWORK_ERROR) {
            throw IOException("Ошибка сети")
        }
        return storage.getAllTracks()
    }

    private companion object {
        const val SEARCH_DELAY_MS = 1000L
        const val SIMULATE_NETWORK_ERROR = false
    }
}
