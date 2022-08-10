package com.developersancho.domain.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.data.model.dto.toSearchItemDtoList
import com.developersancho.data.repository.SearchRepository

class SearchPagingSource(
    internal val repository: SearchRepository,
    internal val searchParams: Search.Params
) : PagingSource<Int, SearchItemDto>() {

    companion object {
        private const val START_INDEX = 1
        private const val QUERY_LIMIT = "limit"
        private const val QUERY_OFFSET = "offset"
        private const val QUERY_TERM = "term"
        private const val QUERY_ENTITY = "entity"
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(START_INDEX) ?: anchorPage?.nextKey?.minus(START_INDEX)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItemDto> {
        val page = params.key ?: START_INDEX
        return try {
            val queryParams = hashMapOf<String, String>()
            queryParams[QUERY_LIMIT] = searchParams.limit.toString()
            queryParams[QUERY_OFFSET] = page.toString()
            searchParams.keyword?.let { queryParams[QUERY_TERM] = it }
            searchParams.entity?.let { queryParams[QUERY_ENTITY] = it }

            val response = repository.search(queryParams)
            val searchList = response.results.orEmpty().toSearchItemDtoList()

            LoadResult.Page(
                data = searchList,
                prevKey = if (page == START_INDEX) null else page - START_INDEX,
                nextKey = if (searchList.isEmpty()) null else page + START_INDEX
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}