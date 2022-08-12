package com.developersancho.hb.compose.features.detail

import com.developersancho.hb.compose.app.extensions.SearchItemNavArgs

data class DetailViewState(
    val dto: SearchItemNavArgs? = null
)

sealed class DetailEvent {
    object LoadDetail : DetailEvent()
}