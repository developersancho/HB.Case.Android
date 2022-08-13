package com.developersancho.hb.compose.features.detail

import androidx.lifecycle.SavedStateHandle
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.hb.compose.app.extensions.SearchItemNavArgs
import com.developersancho.hb.compose.features.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    MviViewModel<BaseViewState<DetailViewState>, DetailEvent>() {

    private val searchItemDto = savedStateHandle.navArgs() as SearchItemNavArgs

    override fun onTriggerEvent(eventType: DetailEvent) {
        when (eventType) {
            DetailEvent.LoadDetail -> loadDetail()
        }
    }

    private fun loadDetail() {
        setState(BaseViewState.Success(DetailViewState(dto = searchItemDto)))
    }
}