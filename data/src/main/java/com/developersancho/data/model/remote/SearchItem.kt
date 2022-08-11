package com.developersancho.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchItem(
    @Json(name = "trackId") val trackId: Int?,
    @Json(name = "trackName") val trackName: String?,
    @Json(name = "collectionPrice") val collectionPrice: Double?,
    @Json(name = "artworkUrl100") val artworkUrl100: String?,
    @Json(name = "releaseDate") val releaseDate: String?,
    @Json(name = "collectionName") val collectionName: String?,
    @Json(name = "collectionId") val collectionId: Int?,
    @Json(name = "currency") val currency: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "artistName") val artistName: String?
)
