package com.developersancho.data.model.dto

import com.developersancho.data.model.remote.SearchItem

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
)

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