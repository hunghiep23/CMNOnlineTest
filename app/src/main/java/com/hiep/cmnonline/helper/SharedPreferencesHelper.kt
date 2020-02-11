package com.hiep.cmnonline.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper private constructor(context: Context) {
    private val name = "CMN"
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    var skipTutorial: Boolean
        get() = sharedPreferences.getBoolean(KEY_SKIP_TUTORIAL, false)
        set(value) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(KEY_SKIP_TUTORIAL, value)
            editor.apply()
        }

    var logged:Boolean
    get()=sharedPreferences.getBoolean(KEY_LOGGED,false)
    set(value) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_LOGGED, value)
        editor.apply()
    }

    companion object {
        var instance: SharedPreferencesHelper? = null
        const val KEY_SKIP_TUTORIAL = "skip_tutorial"
        const val KEY_LOGGED = "logged"

        @JvmStatic
        fun getInstance(context: Context): SharedPreferencesHelper {
            if (instance == null)
                instance = SharedPreferencesHelper(context)
            return instance!!
        }
    }
}