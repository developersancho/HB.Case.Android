package com.developersancho.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "resultCount") val resultCount: Int?,
    @Json(name = "results") val results: List<SearchItem>?
)