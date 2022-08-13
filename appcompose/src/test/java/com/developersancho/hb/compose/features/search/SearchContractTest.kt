package com.developersancho.hb.compose.features.search

import androidx.paging.PagingData
import com.developersancho.data.model.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class SearchContractTest {
    private lateinit var event: SearchEvent

    private lateinit var state: SearchViewState

    @Test
    fun verifyEvent_SearchByText() {
        event = SearchEvent.SearchByText(keyword = "John")

        val eventSearchByText = event as SearchEvent.SearchByText
        Assert.assertEquals(event, eventSearchByText)
    }

    @Test
    fun verifyEvent_SearchByFilterType() {
        event = SearchEvent.SearchByFilterType(filterType = SearchFilterType.MUSIC)

        val eventSearchByFilterType = event as SearchEvent.SearchByFilterType
        Assert.assertEquals(event, eventSearchByFilterType)
    }

    @Test
    fun verifyState_SearchViewState() {
        val pagedData: Flow<PagingData<SearchItemDto>> =
            flow { emit(PagingData.empty()) }
        state = SearchViewState(pagedData)

        Assert.assertEquals(pagedData, state.pagedData)
    }
}