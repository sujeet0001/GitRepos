package com.gitrepos.room

import androidx.room.*
import com.gitrepos.domain.main.entity.ListEntity

@Dao
interface RepoDao {

    // your queries here

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(store: List<ListEntity>)

    @Query("select * from repoTable")
    suspend fun getRepos(): List<ListEntity>

    @Query("delete from repoTable")
    suspend fun clearRepoTable()

}