package com.developersancho.hb.features.detail

import androidx.lifecycle.SavedStateHandle
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    MviViewModel<BaseViewState<DetailViewState>, DetailEvent>() {

    private val searchItemDto: SearchItemDto? = savedStateHandle[DetailFragment.ARG_SEARCH_ITEM_DTO]

    override fun onTriggerEvent(eventType: DetailEvent) {
        when (eventType) {
            DetailEvent.LoadDetail -> loadDetail()
        }
    }

    private fun loadDetail() {
        setState(BaseViewState.Success(DetailViewState(dto = searchItemDto)))
    }
}