package com.gitrepos.utils

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.applyIoToMainSchedulerOnSingle(): Single<T> = this.compose {
    it.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
}

inline fun <T> Single<T>.subscribeToSingle(
    crossinline success: (T) -> Unit,
    crossinline error: (Throwable) -> Unit = {}
)
        : Disposable {
    return this.subscribe({
        success(it)
    }, {
        error(it)
    })
}

fun Completable.applyIoToMainSchedulerOnCompletable(): Completable = this.compose {
    it.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
}

inline fun Completable.subscribeToCompletable(
    crossinline success: () -> Unit,
    crossinline error: (Throwable) -> Unit = {}
)
        : Disposable {
    return this.subscribe({
        success()
    }, {
        error(it)
    })
}
