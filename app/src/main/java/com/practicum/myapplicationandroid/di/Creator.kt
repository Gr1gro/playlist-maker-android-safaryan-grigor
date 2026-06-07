package com.practicum.myapplicationandroid.di

import com.practicum.myapplicationandroid.data.Storage
import com.practicum.myapplicationandroid.data.TracksRepositoryImpl
import com.practicum.myapplicationandroid.data.network.RetrofitNetworkClient
import com.practicum.myapplicationandroid.domain.repository.TracksRepository

object Creator {

    fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(RetrofitNetworkClient(Storage()))
    }
}
