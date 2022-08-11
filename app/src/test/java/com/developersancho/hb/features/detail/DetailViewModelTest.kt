package com.developersancho.hb.features.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.testutils.MockkUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DetailViewModelTest : MockkUnitTest() {

    private fun callViewModel(
        savedStateHandle: SavedStateHandle = SavedStateHandle()
    ): DetailViewModel {
        return DetailViewModel(savedStateHandle)
    }

    @Test
    fun verifyOnTriggerEvent_LoadDetail() = runTest {
        // Arrange (Given)
        val dto = SearchItemDto.init()
        val savedStateHandle = SavedStateHandle(mapOf(DetailFragment.ARG_SEARCH_ITEM_DTO to dto))
        val viewModel = callViewModel(savedStateHandle)

        // Act (When)
        viewModel.onTriggerEvent(DetailEvent.LoadDetail)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}