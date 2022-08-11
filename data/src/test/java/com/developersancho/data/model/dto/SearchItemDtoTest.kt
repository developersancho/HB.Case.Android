package com.developersancho.data.model.dto

import com.developersancho.data.model.remote.SearchItem
import org.junit.Assert
import org.junit.Test

class SearchItemDtoTest {

    @Test
    fun check_CorrectAttributes() {
        // Given
        val trackId = 120954025
        val trackName = "Upside Down"

        val dto = SearchItemDto(
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
        Assert.assertEquals(trackId, dto.trackId)
        Assert.assertEquals(trackName, dto.trackName)
        Assert.assertNull(dto.collectionPrice)
    }

    @Test
    fun check_ToSearchItemDto() {
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

        // When
        val dto = searchItem.toSearchItemDto()

        // Then
        Assert.assertEquals(searchItem.trackId, dto.trackId)
        Assert.assertEquals(searchItem.trackName, dto.trackName)
        Assert.assertNull(dto.collectionPrice)
    }

    @Test
    fun check_ToSearchItemDtoList() {
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

        val searchItemList = listOf(searchItem)

        // When
        val dtoList = searchItemList.toSearchItemDtoList()

        // Then
        Assert.assertEquals(searchItemList.first().trackId, dtoList.first().trackId)
        Assert.assertEquals(searchItemList.first().trackName, dtoList.first().trackName)
        Assert.assertNull(dtoList.first().collectionPrice)
    }
}