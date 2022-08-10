package com.developersancho.hb.features.search

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

    override fun onTriggerEvent(eventType: SearchEvent) {
        when (eventType) {
            is SearchEvent.Search -> search(eventType.query, eventType.entity)
        }
    }

    private fun search(query: String?, entity: String?) = safeLaunch {
        setState(BaseViewState.Loading)
        val params = Search.Params(query, entity)
        val pagedFlow = search(params).cachedIn(scope = viewModelScope)
        setState(BaseViewState.Success(SearchViewState(pagedData = pagedFlow)))
    }
}