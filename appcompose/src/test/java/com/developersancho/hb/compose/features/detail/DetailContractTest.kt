package com.developersancho.hb.compose.features.detail

import org.junit.Assert
import org.junit.Test

class DetailContractTest {
    private lateinit var event: DetailEvent

    private lateinit var state: DetailViewState

    @Test
    fun verifyEvent_LoadDetail() {
        event = DetailEvent.LoadDetail

        val eventLoadDetail = event as DetailEvent.LoadDetail
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyState_DetailViewState() {
        state = DetailViewState(null)

        Assert.assertNull(state.dto)
    }
}