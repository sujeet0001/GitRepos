package com.gitrepos.injection

import com.gitrepos.room.RepoDB
import com.gitrepos.room.RepoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DBModule {
    @Provides
    @JvmStatic
    @Singleton
    internal fun bindRepoDao(repoDB: RepoDB): RepoDao {
        return repoDB.getRepoDao()
    }
}