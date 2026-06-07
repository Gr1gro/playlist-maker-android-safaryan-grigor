package com.practicum.myapplicationandroid.domain.model

data class Playlist(
    val id: Long,
    val name: String,
    val description: String = "",
    val coverUri: String? = null,
    val trackIds: List<Long> = emptyList(),
)
