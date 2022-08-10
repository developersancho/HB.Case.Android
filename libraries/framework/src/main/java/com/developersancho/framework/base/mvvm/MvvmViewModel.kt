package com.developersancho.framework.base.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersancho.framework.network.DataState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

open class MvvmViewModel: ViewModel() {
    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(SAFE_LAUNCH_EXCEPTION).e(exception)
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {}

    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    protected suspend fun <T> execute(
        callFlow: Flow<DataState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Error -> handleError(state.error)
                    is DataState.Success -> completionHandler.invoke(state.result)
                }
            }
    }

    /*
   private fun handleError(throwable: Throwable) {
       when (throwable) {
                   // case no internet connection
                   is UnknownHostException -> {
                       noInternetConnectionEvent.call()
                   }
                   is ConnectException -> {
                       noInternetConnectionEvent.call()
                   }
                   // case request time out
                   is SocketTimeoutException -> {
                       connectTimeoutEvent.call()
                   }
                   else -> {
                       // convert throwable to base exception to get error information
                       val baseException = throwable.toBaseException()
                       when (baseException.httpCode) {
                           HttpURLConnection.HTTP_UNAUTHORIZED -> {
                               errorMessage.value = baseException.message
                           }
                           HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                               errorMessage.value = baseException.message
                           }
                           else -> {
                               unknownErrorEvent.call()
                           }
                       }
                   }
               }
   }
*/

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}