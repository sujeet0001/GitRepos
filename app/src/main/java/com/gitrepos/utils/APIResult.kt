package com.gitrepos.utils

sealed class APIResult<T> {

    data class Progress<T>(var loading: Boolean) : APIResult<T>()
    data class Success<T>(var data: T) : APIResult<T>()
    data class ApiError<T>(val e: Throwable) : APIResult<T>()
    companion object {

        fun <T> loading(isLoading: Boolean): APIResult<T> = Progress(isLoading)
        fun <T> success(data: T): APIResult<T> = Success(data)
        fun <T> apiError(e: Throwable): APIResult<T> = ApiError(e)

    }
}