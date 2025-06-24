package com.dewipuspitasari0020.linakost.util


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = "user_preference"
)

class UserDataStore(private val context: Context) {

    private companion object {
        val USER_ID_KEY = stringPreferencesKey("user_id")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    suspend fun saveUserData(userId: String, userEmail: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_EMAIL_KEY] = userEmail
        }
    }

    fun getUserId(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }
    }

    fun getUserEmail(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL_KEY]
        }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}