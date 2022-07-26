package com.gitrepos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gitrepos.domain.main.entity.ListEntity
import com.gitrepos.room.typeconvertor.ConvertListEntity
import com.gitrepos.room.typeconvertor.ConvertOwner

@Database(entities = [ListEntity::class], version = 1, exportSchema = false)

@TypeConverters(
    ConvertListEntity::class,
    ConvertOwner::class
)

abstract class RepoDB : RoomDatabase() {

    abstract fun getRepoDao(): RepoDao

}