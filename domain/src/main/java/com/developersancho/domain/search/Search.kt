package com.developersancho.domain.search

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.data.repository.SearchRepository
import com.developersancho.framework.usecase.RequestPagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Search @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: SearchRepository
) : RequestPagingUseCase<Search.Params, SearchItemDto>() {

    data class Params(
        val query: String? = null,
        val entity: String? = null,
        val limit: Int = 20
    )

    override fun execute(params: Params): Flow<PagingData<SearchItemDto>> {
        val pagingConfig = PagingConfig(pageSize = params.limit)
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { SearchPagingSource(repository, params) }
        ).flow
    }
}