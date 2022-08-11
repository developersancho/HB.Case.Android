package com.developersancho.data.repository

import com.developersancho.data.remote.service.SearchService
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SearchRepositoryTest : MockkUnitTest() {
    @MockK(relaxed = true)
    lateinit var searchService: SearchService

    private lateinit var repository: SearchRepository

    override fun onCreate() {
        super.onCreate()
        repository = SearchRepository(searchService)
    }

    @Test
    fun check_search() = runTest {
        // Given
        val searchOptions = hashMapOf<String, String>()
        val slotOptions = slot<Map<String, String>>()

        // When
        repository.search(searchOptions)

        // Then
        coVerify {
            searchService.search(options = capture(slotOptions))
        }

        Assert.assertEquals(searchOptions, slotOptions.captured)
    }

}