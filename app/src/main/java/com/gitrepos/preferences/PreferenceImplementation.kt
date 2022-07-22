package com.gitrepos.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceImplementation @Inject constructor(
        private val sharedPreferences: SharedPreferences

) : Preferences {

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun getString(key: String, default: String): String {
        return sharedPreferences.getString(key, default) ?: default
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    override fun setString(key: String, value: String) {
        return sharedPreferences.edit().putString(key, value).apply()
    }

    override fun setBoolean(key: String, value: Boolean) {
        return sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun setInt(key: String, value: Int) {
        return sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun setFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0.0f)
    }

}