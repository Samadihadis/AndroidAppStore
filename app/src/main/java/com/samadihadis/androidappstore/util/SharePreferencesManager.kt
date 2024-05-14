package com.samadihadis.androidappstore.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharePreferencesManager(context: Context) {


    private val pref by lazy {
        context.getSharedPreferences(PREF_FILE_NAME, AppCompatActivity.MODE_PRIVATE)
    }

    private val editor by lazy {
        pref.edit()
    }


    fun saveString(key: String, value: String) {
        editor.apply {
            putString(key, value)
            commit()
        }
    }
    fun retrieveString(key: String): String? {
        return pref.getString(key, "")
    }

    fun saveInt(key: String, value: Int) {
        editor.apply {
            putInt(key, value)
            commit()
        }
    }
    fun retrieveInt(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.apply {
            putBoolean(key, value)
            commit()
        }
    }
    fun retrieveBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }


    companion object {
        private const val PREF_FILE_NAME = "share_pref"
    }
}