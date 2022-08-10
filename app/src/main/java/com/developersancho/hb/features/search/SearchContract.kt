package com.developersancho.hb.features.search

import androidx.paging.PagingData
import com.developersancho.data.model.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchViewState(
    val pagedData: PagingData<SearchItemDto> = PagingData.empty()
)

sealed class SearchEvent {
    data class SearchByText(val keyword: String?) : SearchEvent()
    data class SearchByFilterType(val filterType: SearchFilterType? = null) : SearchEvent()
}