package com.developersancho.hb.compose.features.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extensions.cast
import com.developersancho.hb.compose.R
import com.developersancho.hb.compose.app.extensions.SearchItemNavArgs
import com.developersancho.hb.compose.app.widgets.HBToolbarWithNavIcon
import com.developersancho.hb.compose.app.widgets.LoadingView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@Destination(navArgsDelegate = SearchItemNavArgs::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        HBToolbarWithNavIcon(
            R.string.toolbar_detail_title,
            pressOnBack = {
                navigator.navigateUp()
            }
        )
    }, content = { paddings ->
        when (uiState) {
            is BaseViewState.Success -> DetailContent(
                paddingValues = paddings,
                state = uiState.cast<BaseViewState.Success<DetailViewState>>().data
            )
            is BaseViewState.Empty -> {
                // todo: can be develop empty view -> EmptyView()
            }
            is BaseViewState.Error -> {
                // todo: can be develop error view -> ErrorView
            }
            is BaseViewState.Loading -> LoadingView()
        }
    })

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(DetailEvent.LoadDetail)
    })
}

@Composable
fun DetailContent(paddingValues: PaddingValues, state: DetailViewState) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item("header") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(top = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.dto?.artworkUrl100)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(size = 16.dp))
                )
            }
        }
    }
}
