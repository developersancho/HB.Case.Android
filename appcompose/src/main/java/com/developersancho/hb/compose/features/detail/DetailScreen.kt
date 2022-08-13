package com.developersancho.hb.compose.features.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extensions.cast
import com.developersancho.framework.extensions.toReleaseDate
import com.developersancho.hb.compose.R
import com.developersancho.hb.compose.app.extensions.SearchItemNavArgs
import com.developersancho.hb.compose.app.extensions.toSearchItemNavArgs
import com.developersancho.hb.compose.app.theme.HBCaseTheme
import com.developersancho.hb.compose.app.widgets.HBToolbarWithNavIcon
import com.developersancho.hb.compose.app.widgets.LoadingView
import com.developersancho.hb.compose.features.detail.view.DetailTextRow
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
            is BaseViewState.Success -> DetailPage(
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
private fun DetailPage(paddingValues: PaddingValues, state: DetailViewState) {
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
                        .error(R.drawable.ic_error_image)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(size = 16.dp))
                )
            }
        }

        item("content") {
            Box(modifier = Modifier.padding(8.dp)) {
                DetailContent(state.dto)
            }
        }
    }
}

@Composable
private fun DetailContent(dto: SearchItemNavArgs?) {
    Card(elevation = 8.dp, shape = MaterialTheme.shapes.large) {
        Column(modifier = Modifier.fillMaxWidth()) {
            dto?.trackName?.let {
                DetailTextRow(key = stringResource(id = R.string.text_track_name), value = it)
            }
            dto?.collectionName?.let {
                DetailTextRow(key = stringResource(id = R.string.text_collection_name), value = it)
            }
            dto?.artistName?.let {
                DetailTextRow(key = stringResource(id = R.string.text_artist_name), value = it)
            }
            dto?.description?.let {
                DetailTextRow(key = stringResource(id = R.string.text_description), value = it)
            }
            dto?.collectionPrice?.let {
                DetailTextRow(
                    key = stringResource(id = R.string.text_price),
                    value = "${it}-${dto.currency}"
                )
            }
            dto?.releaseDate?.let {
                DetailTextRow(
                    key = stringResource(id = R.string.text_release_date),
                    value = it.toReleaseDate()
                )
            }
        }
    }
}

@Preview(name = "DetailTextRowPreview", showBackground = true)
@Composable
private fun DetailTextRowPreview() {
    HBCaseTheme {
        DetailContent(SearchItemDto.init().toSearchItemNavArgs())
    }
}
