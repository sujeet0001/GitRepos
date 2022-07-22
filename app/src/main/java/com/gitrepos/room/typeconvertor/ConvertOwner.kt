package com.gitrepos.room.typeconvertor

import androidx.room.TypeConverter
import com.gitrepos.domain.main.entity.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConvertOwner {

    @TypeConverter
    fun fromString(value: String?): Owner? {
        val obj = object : TypeToken<Owner>() {}.type
        return Gson().fromJson(value, obj)
    }

    @TypeConverter
    fun fromObj(obj: Owner?): String? {
        return Gson().toJson(obj)
    }

}