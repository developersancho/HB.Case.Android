package com.developersancho.hb.features.search

import androidx.paging.PagingData
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.domain.search.Search
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SearchViewModelTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var search: Search

    @SpyK
    @InjectMockKs
    lateinit var viewModel: SearchViewModel


    @Test
    fun verifyOnTriggerEvent_SearchByText() = runTest {
        // Arrange (Given)
        val searchKeyword = "John"
        every { search.invoke(any()) } returns flow {
            emit(PagingData.from(listOf(SearchItemDto.init())))
        }

        // Act (When)
        viewModel.onTriggerEvent(SearchEvent.SearchByText(searchKeyword))

        // Assert (Then)
        coVerify { search.invoke(any()) }
    }

    @Test
    fun verifyOnTriggerEvent_SearchByFilterType() = runTest {
        // Arrange (Given)
        viewModel.searchKeyword = "John"
        every { search.invoke(any()) } returns flow {
            emit(PagingData.from(listOf(SearchItemDto.init())))
        }

        // Act (When)
        viewModel.onTriggerEvent(SearchEvent.SearchByFilterType(SearchFilterType.MUSIC))

        // Assert (Then)
        coVerify { search.invoke(any()) }
        Assert.assertNotNull(viewModel.searchKeyword)
        Assert.assertNotNull(viewModel.searchFilterType)
    }
}