package com.gitrepos.domain.main.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "repoTable")
data class ListEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int?,
    val name: String?,
    val owner: Owner?
)

@Keep
data class Owner(
    val login: String?,
    val avatar_url: String?
)
