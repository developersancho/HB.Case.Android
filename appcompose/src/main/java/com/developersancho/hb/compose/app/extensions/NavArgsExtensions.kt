package com.developersancho.hb.compose.app.extensions

import com.developersancho.data.model.dto.SearchItemDto


data class SearchItemNavArgs(
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
)

fun SearchItemDto.toSearchItemNavArgs() = SearchItemNavArgs(
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