package com.practicum.myapplicationandroid.data

import com.practicum.myapplicationandroid.domain.model.Track
import com.practicum.myapplicationandroid.util.parseTrackTimeToMillis

/**
 * Mock-каталог треков.
 *
 * Обложки подключены через [artworkUrl], то есть по ссылкам.
 * Если интернет на эмуляторе работает, Coil загрузит картинки автоматически.
 * Если ссылка не загрузится, вместо картинки появится стандартная иконка музыки.
 */
object MockTracks {

    private const val ART_SUMMERTIME_SADNESS =
        "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=600&auto=format&fit=crop&q=80"
    private const val ART_YOUNG_AND_BEAUTIFUL =
        "https://images.unsplash.com/photo-1518895949257-7621c3c786d7?w=600&auto=format&fit=crop&q=80"
    private const val ART_BORN_TO_DIE =
        "https://images.unsplash.com/photo-1495567720989-cebdbdd97913?w=600&auto=format&fit=crop&q=80"
    private const val ART_VIDEO_GAMES =
        "https://images.unsplash.com/photo-1493246507139-91e8fad9978e?w=600&auto=format&fit=crop&q=80"
    private const val ART_WEST_COAST =
        "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&auto=format&fit=crop&q=80"
    private const val ART_BLUE_JEANS =
        "https://images.unsplash.com/photo-1487180142328-054b783fc471?w=600&auto=format&fit=crop&q=80"
    private const val ART_RIDE =
        "https://images.unsplash.com/photo-1500534314209-a25ddb2bd429?w=600&auto=format&fit=crop&q=80"
    private const val ART_LOVE =
        "https://images.unsplash.com/photo-1516280440614-37939bbacd6a?w=600&auto=format&fit=crop&q=80"
    private const val ART_DOIN_TIME =
        "https://images.unsplash.com/photo-1500534623283-312aade485b7?w=600&auto=format&fit=crop&q=80"
    private const val ART_VENICE_BITCH =
        "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&auto=format&fit=crop&q=80"
    private const val ART_RADIO =
        "https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=600&auto=format&fit=crop&q=80"
    private const val ART_NATIONAL_ANTHEM =
        "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=600&auto=format&fit=crop&q=80"
    private const val ART_BROOKLYN_BABY =
        "https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?w=600&auto=format&fit=crop&q=80"
    private const val ART_CHEMTRAILS =
        "https://images.unsplash.com/photo-1500534314209-a25ddb2bd429?w=600&auto=format&fit=crop&q=80"
    private const val ART_AW =
        "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=600&auto=format&fit=crop&q=80"

    val lanaTracks: List<Track> = listOf(
        Track(1, "Summertime Sadness", "Lana Del Rey", parseTrackTimeToMillis("4:25"), artworkUrl = ART_SUMMERTIME_SADNESS),
        Track(2, "Young and Beautiful", "Lana Del Rey", parseTrackTimeToMillis("3:56"), artworkUrl = ART_YOUNG_AND_BEAUTIFUL),
        Track(3, "Born To Die", "Lana Del Rey", parseTrackTimeToMillis("4:46"), artworkUrl = ART_BORN_TO_DIE),
        Track(4, "Video Games", "Lana Del Rey", parseTrackTimeToMillis("4:42"), artworkUrl = ART_VIDEO_GAMES),
        Track(5, "West Coast", "Lana Del Rey", parseTrackTimeToMillis("4:17"), artworkUrl = ART_WEST_COAST),
        Track(6, "Blue Jeans", "Lana Del Rey", parseTrackTimeToMillis("3:30"), artworkUrl = ART_BLUE_JEANS),
        Track(7, "Ride", "Lana Del Rey", parseTrackTimeToMillis("4:49"), artworkUrl = ART_RIDE),
        Track(8, "Love", "Lana Del Rey", parseTrackTimeToMillis("4:32"), artworkUrl = ART_LOVE),
        Track(9, "Doin' Time", "Lana Del Rey", parseTrackTimeToMillis("3:22"), artworkUrl = ART_DOIN_TIME),
        Track(10, "Venice Bitch", "Lana Del Rey", parseTrackTimeToMillis("9:37"), artworkUrl = ART_VENICE_BITCH),
        Track(11, "Radio", "Lana Del Rey", parseTrackTimeToMillis("3:35"), artworkUrl = ART_RADIO),
        Track(12, "National Anthem", "Lana Del Rey", parseTrackTimeToMillis("3:50"), artworkUrl = ART_NATIONAL_ANTHEM),
        Track(13, "Brooklyn Baby", "Lana Del Rey", parseTrackTimeToMillis("5:51"), artworkUrl = ART_BROOKLYN_BABY),
        Track(14, "Chemtrails Over The Country Club", "Lana Del Rey", parseTrackTimeToMillis("4:31"), artworkUrl = ART_CHEMTRAILS),
        Track(15, "A&W", "Lana Del Rey", parseTrackTimeToMillis("7:13"), artworkUrl = ART_AW),
    )

    val searchCatalogTracks: List<Track> = lanaTracks

    val allTracks: List<Track> = lanaTracks
}
