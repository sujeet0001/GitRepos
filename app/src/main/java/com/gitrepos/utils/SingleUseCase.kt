package com.gitrepos.utils

import io.reactivex.Single

interface SingleUseCase<T, PARAMS> {
    fun execute(param: PARAMS): Single<T>
}