package com.gitrepos.data.main.repository

import com.gitrepos.data.main.service.MainActivityService
import com.gitrepos.domain.main.entity.ListEntity
import io.reactivex.Single
import javax.inject.Inject

class MainActivityRepo @Inject constructor(
    private val mainActivityService: MainActivityService
) {

    fun callAPI(name: String): Single<List<ListEntity>> {
        return mainActivityService.getRepositories(name)
    }

}