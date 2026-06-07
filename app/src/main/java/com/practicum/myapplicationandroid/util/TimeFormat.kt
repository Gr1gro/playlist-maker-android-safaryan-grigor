package com.practicum.myapplicationandroid.util

import java.util.Locale
import java.util.concurrent.TimeUnit

fun formatTrackDuration(millis: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60
    return String.format(Locale.getDefault(), "%d:%02d", minutes, seconds)
}

fun formatTotalDuration(millis: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
    return "$minutes минут"
}

fun parseTrackTimeToMillis(time: String): Long {
    val parts = time.split(":")
    val minutes = parts[0].toLong()
    val seconds = parts[1].toLong()
    return TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds)
}
