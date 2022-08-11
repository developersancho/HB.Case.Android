package com.developersancho.hb.features.detail

import com.developersancho.data.model.dto.SearchItemDto

data class DetailViewState(
    val dto: SearchItemDto? = null
)

sealed class DetailEvent {
    object LoadDetail : DetailEvent()
}