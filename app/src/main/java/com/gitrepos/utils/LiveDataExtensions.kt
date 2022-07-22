package com.gitrepos.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<APIResult<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (e: Throwable) -> Unit = {},
    crossinline onProgress: (Boolean) -> Unit = {},
) {
    this.observe(owner, Observer {
        when (it) {
            is APIResult.Success -> onSuccess(it.data)
            is APIResult.ApiError -> onError(it.e)
            is APIResult.Progress -> onProgress(it.loading)
        }
    })
}