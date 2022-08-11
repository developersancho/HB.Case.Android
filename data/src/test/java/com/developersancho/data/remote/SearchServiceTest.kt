package com.developersancho.data.remote

import com.developersancho.data.remote.service.SearchService
import com.developersancho.testutils.BaseServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SearchServiceTest : BaseServiceTest<SearchService>(SearchService::class) {
    override val baseUrl: String
        get() = "https://itunes.apple.com/"

    @Test
    fun requestLiveSearch() = runTest {
        // Given
        val options = hashMapOf<String, String>()
        options["limit"] = "20"
        options["term"] = "John"

        // When
        val response = serviceLive.search(options)

        // Then
        Assert.assertEquals(928911988, response.results?.first()?.trackId)
        Assert.assertEquals("John Wick", response.results?.first()?.trackName)
        Assert.assertEquals(20, response.resultCount)
    }
}