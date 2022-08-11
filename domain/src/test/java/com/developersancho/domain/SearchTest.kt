package com.developersancho.domain

import com.developersancho.data.repository.SearchRepository
import com.developersancho.domain.search.Search
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var repository: SearchRepository

    @SpyK
    @InjectMockKs
    private lateinit var search: Search

    @Test
    fun verifyExecute() = runTest {
        // Given
        val params = Search.Params(keyword = null, entity = null)

        // When
        search.invoke(params)

        // Then
        coVerify { search.invoke(any()) }
    }

}