package com.developersancho.data.model.dto

import android.os.Parcelable
import com.developersancho.data.model.remote.SearchItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchItemDto(
    val trackId: Int?,
    val trackName: String?,
    val collectionPrice: Double?,
    val artworkUrl100: String?,
    val releaseDate: String?,
    val collectionName: String?,
    val collectionId: Int?,
    val currency: String?,
    val description: String?,
    val artistName: String?
) : Parcelable {
    companion object {
        fun init() = SearchItemDto(
            trackId = 120954025,
            trackName = "Upside Down",
            collectionPrice = 10.99,
            artworkUrl100 = "http://a1.itunes.apple.com/r10/Music/3b/6a/33/mzi.qzdqwsel.100x100-75.jpg\"",
            releaseDate = null,
            collectionName = "Sing-a-Longs and Lullabies for the Film Curious George",
            collectionId = 120954021,
            currency = "USD",
            description = null,
            artistName = "Jack Johnson"
        )
    }
}

fun SearchItem.toSearchItemDto() = SearchItemDto(
    trackId,
    trackName,
    collectionPrice,
    artworkUrl100,
    releaseDate,
    collectionName,
    collectionId,
    currency,
    description,
    artistName
)

fun List<SearchItem>.toSearchItemDtoList() = map { it.toSearchItemDto() }