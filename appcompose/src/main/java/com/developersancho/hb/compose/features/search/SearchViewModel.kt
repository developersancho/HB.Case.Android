package com.developersancho.hb.compose.features.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.developersancho.domain.search.Search
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val search: Search
) : MviViewModel<BaseViewState<SearchViewState>, SearchEvent>() {

    companion object {
        private const val MAX_SEARCH_LENGTH = 3
    }

    var searchFilterType: SearchFilterType? = null
    var searchKeyword: String? = null

    override fun onTriggerEvent(eventType: SearchEvent) {
        when (eventType) {
            is SearchEvent.SearchByText -> searchByText(eventType.keyword)
            is SearchEvent.SearchByFilterType -> searchByFilterType(eventType.filterType)
        }
    }

    private fun searchByText(keyword: String?) = safeLaunch {
        if (keyword.orEmpty().length < MAX_SEARCH_LENGTH) return@safeLaunch
        searchKeyword = keyword
        setState(BaseViewState.Loading)
        val params = Search.Params(searchKeyword, searchFilterType?.type)
        val pagedFlow = search(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Success(SearchViewState(pagedData = pagedFlow)))
    }

    private fun searchByFilterType(filterType: SearchFilterType?) = safeLaunch {
        if (searchKeyword.orEmpty().length < MAX_SEARCH_LENGTH) return@safeLaunch
        searchFilterType = filterType
        setState(BaseViewState.Loading)
        val params = Search.Params(searchKeyword, searchFilterType?.type)
        val pagedFlow = search(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Success(SearchViewState(pagedData = pagedFlow)))
    }
}