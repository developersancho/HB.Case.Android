package com.developersancho.data.remote.service

import com.developersancho.data.model.remote.SearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {
    @GET(SEARCH)
    suspend fun search(
        @QueryMap options: Map<String, Any>
    ): SearchResponse

    companion object {
        const val SEARCH = "search"
    }
}