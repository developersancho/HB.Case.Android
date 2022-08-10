package com.developersancho.framework.base.mvi

sealed interface BaseViewState<out T> {
    object Loading : BaseViewState<Nothing>
    object Empty : BaseViewState<Nothing>
    data class Success<T>(val data: T) : BaseViewState<T>
    data class Error(val throwable: Throwable? = null) : BaseViewState<Nothing>
}