package com.developersancho.hb.compose.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extensions.cast
import com.developersancho.hb.compose.R
import com.developersancho.hb.compose.app.extensions.rememberFlowWithLifecycle
import com.developersancho.hb.compose.app.extensions.toSearchItemNavArgs
import com.developersancho.hb.compose.app.theme.HBCaseTheme
import com.developersancho.hb.compose.app.widgets.LoadingView
import com.developersancho.hb.compose.app.widgets.SegmentedControl
import com.developersancho.hb.compose.features.destinations.DetailScreenDestination
import com.developersancho.hb.compose.features.search.view.SearchRow
import com.developersancho.hb.compose.features.search.view.SearchTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.red_primary))
            ) {
                var searchQuery by remember { mutableStateOf(TextFieldValue(viewModel.searchKeyword.orEmpty())) }

                SearchTextField(
                    value = searchQuery,
                    onValueChange = { value ->
                        searchQuery = value
                        coroutineScope.launch {
                            delay(300)
                            viewModel.onTriggerEvent(SearchEvent.SearchByText(keyword = value.text))
                        }
                    },
                    hint = stringResource(R.string.hint_search),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .background(
                            color = colorResource(id = R.color.red_shade_20),
                            shape = RoundedCornerShape(percent = 10)
                        ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }
        },
        content = { paddings ->
            Column(
                modifier = Modifier.padding(paddings),
            ) {
                val context = LocalContext.current
                val segments = remember {
                    listOf(
                        context.getString(R.string.chip_movie),
                        context.getString(R.string.chip_music),
                        context.getString(R.string.chip_ebook),
                        context.getString(R.string.chip_podcast)
                    )
                }
                var selectedSegment by rememberSaveable("selectedSegment") { mutableStateOf(segments.first()) }

                SegmentedControl(
                    segments,
                    selectedSegment,
                    onSegmentSelected = {
                        selectedSegment = it
                        coroutineScope.launch {
                            delay(300)
                            viewModel.onTriggerEvent(
                                SearchEvent.SearchByFilterType(
                                    filterType = SearchFilterType.fromName(context, selectedSegment)
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .background(
                            color = colorResource(id = R.color.gray_100),
                            MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        text = it,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                when (uiState) {
                    is BaseViewState.Success -> SearchList(
                        paddingValues = paddings,
                        viewState = uiState.cast<BaseViewState.Success<SearchViewState>>().data,
                        selectItem = { dto -> navigator.navigate(DetailScreenDestination(dto.toSearchItemNavArgs())) }
                    )
                    is BaseViewState.Empty -> {
                        // todo: can be develop empty view -> EmptyView()
                    }
                    is BaseViewState.Error -> {
                        // todo: can be develop error view -> ErrorView
                    }
                    is BaseViewState.Loading -> LoadingView()
                }
            }
        }
    )
}

@Composable
fun SearchList(
    paddingValues: PaddingValues,
    viewState: SearchViewState,
    selectItem: (SearchItemDto) -> Unit = {}
) {
    val pagingItems = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = paddingValues,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 12.dp, end = 8.dp),
    ) {
        items(pagingItems.itemCount) { index ->
            pagingItems[index]?.let {
                SearchRow(
                    dto = it,
                    onItemClick = { selectItem.invoke(it) }
                )
            }
        }

        if (pagingItems.loadState.append == LoadState.Loading) {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    HBCaseTheme {
        val pagingItems = listOf(
            SearchItemDto.init(),
            SearchItemDto.init(),
            SearchItemDto.init(),
            SearchItemDto.init()
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 12.dp, end = 8.dp),
        ) {
            items(pagingItems.size) { index ->
                pagingItems[index].let {
                    SearchRow(
                        dto = it,
                        onItemClick = { }
                    )
                }
            }
        }
    }
}