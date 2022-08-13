package com.developersancho.hb.compose.features.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.hb.compose.app.extensions.toSearchItemNavArgs
import com.developersancho.hb.compose.features.destinations.DetailScreenDestination
import com.developersancho.testutils.MockkUnitTest
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

// todo: has bug about Destination argsFrom() for compose destination lib = https://github.com/raamcosta/compose-destinations
class DetailViewModelTest : MockkUnitTest() {

//    @MockK(relaxed = true)
//    lateinit var savedStateHandle: SavedStateHandle
//
//    @SpyK
//    @InjectMockKs
//    lateinit var viewModel: DetailViewModel


    private fun callViewModel(
        savedStateHandle: SavedStateHandle = SavedStateHandle()
    ): DetailViewModel {
        return DetailViewModel(savedStateHandle)
    }

    @Test
    fun verifyOnTriggerEvent_LoadDetail() = runTest {
        // Arrange (Given)
        val dto = SearchItemDto.init().toSearchItemNavArgs()
//        val savedStateHandle =
//            SavedStateHandle(mapOf("trackId" to dto.trackId, "trackName" to dto.trackName.toString()))
        //every { savedStateHandle.navArgs() as SearchItemNavArgs } returns dto
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        val detailScreenDestination = mockk<DetailScreenDestination>(relaxed = true)
//        val savedStateHandle = SavedStateHandle().apply {
//            set("trackId", dto.trackId)
//            set("trackName", dto.trackName)
//        }
        DetailScreenDestination.invoke(dto)
        every { detailScreenDestination.argsFrom(savedStateHandle) } returns dto
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