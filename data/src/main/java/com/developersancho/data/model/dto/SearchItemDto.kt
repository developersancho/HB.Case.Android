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
            artworkUrl100 = "https://is4-ssl.mzstatic.com/image/thumb/Music114/v4/34/f4/d9/34f4d970-ab11-4614-0b9e-aa1a0b849692/00030206730906.rgb.jpg/100x100bb.jpg",
            releaseDate = "2014-10-21T12:00:00Z",
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