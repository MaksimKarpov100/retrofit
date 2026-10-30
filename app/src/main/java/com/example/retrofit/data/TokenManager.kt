package com.example.retrofit.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
object TokenManager {
    val Context.dataStore by preferencesDataStore(name = "settings")
    private val TOKEN_KEY = stringPreferencesKey("auth_key")
    private var appContext: Context? = null

    fun init(context: Context){
        appContext = context.applicationContext
    }

    suspend fun saveToken(token: String) {
        appContext?.dataStore?.edit { settings ->
            settings[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(): String?{
        return appContext?.dataStore?.data?.first()?.get(TOKEN_KEY)
    }
}