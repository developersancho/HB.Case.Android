package com.developersancho.hb.compose.features.search

import androidx.paging.PagingData
import com.developersancho.data.model.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchViewState(
    val pagedData: Flow<PagingData<SearchItemDto>> = emptyFlow()
)

sealed class SearchEvent {
    data class SearchByText(val keyword: String?) : SearchEvent()
    data class SearchByFilterType(val filterType: SearchFilterType? = null) : SearchEvent()
}