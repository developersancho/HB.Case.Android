package com.developersancho.framework.usecase

import com.developersancho.framework.network.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class RequestUseCase<in Params, ReturnType> where ReturnType : Any {

    protected abstract suspend fun execute(params: Params): Flow<DataState<ReturnType>>

    suspend operator fun invoke(params: Params): Flow<DataState<ReturnType>> = execute(params)
        .flowOn(Dispatchers.IO)
}