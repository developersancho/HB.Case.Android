package com.developersancho.data.repository

import androidx.annotation.VisibleForTesting
import com.developersancho.data.remote.service.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val service: SearchService
) {
    suspend fun search(queryParams: Map<String, Any>) = service.search(queryParams)
}