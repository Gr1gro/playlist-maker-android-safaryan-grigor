package com.practicum.myapplicationandroid

import android.app.Application
import com.practicum.myapplicationandroid.data.PlaylistRepository
import com.practicum.myapplicationandroid.data.ThemeRepository
import com.practicum.myapplicationandroid.di.Creator
import com.practicum.myapplicationandroid.domain.repository.TracksRepository

class PlaylistMakerApplication : Application() {

    lateinit var themeRepository: ThemeRepository
        private set

    lateinit var playlistRepository: PlaylistRepository
        private set

    val tracksRepository: TracksRepository
        get() = Creator.getTracksRepository()

    override fun onCreate() {
        super.onCreate()
        themeRepository = ThemeRepository(applicationContext)
        playlistRepository = PlaylistRepository(applicationContext)
    }
}
