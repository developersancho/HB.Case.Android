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
    val artistName: String?,
    val artworkUrl600: String?,
    val artworkUrl512: String?
) : Parcelable

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
    artistName,
    artworkUrl600,
    artworkUrl512
)

fun List<SearchItem>.toSearchItemDtoList() = map { it.toSearchItemDto() }