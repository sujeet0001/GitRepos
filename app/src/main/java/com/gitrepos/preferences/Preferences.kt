package com.gitrepos.preferences

interface Preferences {

    fun getString(key: String) : String

    fun getBoolean(key: String) : Boolean

    fun getBoolean(key: String, default: Boolean) : Boolean

    fun setString(key: String, value :String)

    fun setBoolean(key: String, status: Boolean)

    fun setInt(key: String, value: Int)

    fun getInt(key: String) : Int

    fun getString(key: String, default: String) : String

    fun setFloat(key: String, value: Float)

    fun getFloat(key : String) : Float

}