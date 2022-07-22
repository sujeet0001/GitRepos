package com.gitrepos.data.main.service

import com.gitrepos.domain.main.entity.ListEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MainActivityService {

    @GET("{name}/repos")
    fun getRepositories(@Path("name") name: String): Single<List<ListEntity>>

}