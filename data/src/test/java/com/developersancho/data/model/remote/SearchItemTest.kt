package com.developersancho.data.model.remote

import org.junit.Assert
import org.junit.Test

class SearchItemTest {

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

        // Then
        Assert.assertEquals(trackId, searchItem.trackId)
        Assert.assertEquals(trackName, searchItem.trackName)
        Assert.assertNull(searchItem.collectionPrice)
    }
}