package com.gitrepos.room.typeconvertor

import androidx.room.TypeConverter
import com.gitrepos.domain.main.entity.ListEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConvertListEntity {

    @TypeConverter
    fun fromString(value: String?): List<ListEntity>? {
        val obj = object : TypeToken<List<ListEntity>>() {}.type
        return Gson().fromJson(value, obj)
    }

    @TypeConverter
    fun fromObj(obj: List<ListEntity>?): String? {
        return Gson().toJson(obj)
    }

}