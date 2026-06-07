package com.practicum.myapplicationandroid.domain.model

import androidx.annotation.DrawableRes

data class Track(
    val id: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Long,
    val artworkUrl: String? = null,
    @DrawableRes val artworkResId: Int? = null,
)
