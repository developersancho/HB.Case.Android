package com.developersancho.data.model.remote

import org.junit.Assert
import org.junit.Test

class SearchResponseTest {
    @Test
    fun check_CorrectAttributes() {
        // Given
        val trackId = 120954025
        val trackName = "Upside Down"

        val searchItem = SearchItem(
            trackId = trackId,
            trackName = trackName,
            collectionPrice = null,
            artworkUrl100 = null,
            releaseDate = null,
            collectionName = null,
            collectionId = null,
            currency = null,
            description = null,
            artistName = null
        )

        val response = SearchResponse(
            resultCount = 10,
            results = listOf(searchItem)
        )

        // Then
        Assert.assertEquals(response.results?.first()?.trackId, searchItem.trackId)
        Assert.assertEquals(response.results?.first()?.trackName, searchItem.trackName)
        Assert.assertNull(response.results?.first()?.collectionPrice)
    }
}