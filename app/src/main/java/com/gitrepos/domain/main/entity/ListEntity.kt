package com.gitrepos.domain.main.entity

import androidx.annotation.Keep

@Keep
data class ListEntity(
    val name: String?,
    val owner: Owner?
)

@Keep
data class Owner(
    val login: String?,
    val avatar_url: String?
)
