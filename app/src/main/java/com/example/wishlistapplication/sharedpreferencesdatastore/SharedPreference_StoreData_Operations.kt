package com.example.wishlistapplication.sharedpreferencesdatastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.wishlistapplication.sharedpreferencesdatastore.User_SharedPreference_StoreData_Object.USER_CONTACT_KEY
import com.example.wishlistapplication.sharedpreferencesdatastore.User_SharedPreference_StoreData_Object.USER_DESCRIPTION_KEY
import com.example.wishlistapplication.sharedpreferencesdatastore.User_SharedPreference_StoreData_Object.USER_NAME_KEY
import com.example.wishlistapplication.sharedpreferencesdatastore.User_SharedPreference_StoreData_Object.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

suspend fun saveUserPreferences(context: Context,
                                name: String,
                                contact: String,
                                description: String) {
    context.dataStore.edit { preferences ->
        preferences[USER_NAME_KEY] = name
        preferences[USER_CONTACT_KEY] = contact
        preferences[USER_DESCRIPTION_KEY] = description
    }
}

fun readUserPreferences(context: Context): Flow<User_Data> {
    return context.dataStore.data
        .catch { emit(emptyPreferences()) } // Handle exceptions
        .map { preferences ->
            val name = preferences[USER_NAME_KEY] ?: ""
            val contact = preferences[USER_CONTACT_KEY] ?: ""
            val description = preferences[USER_DESCRIPTION_KEY] ?: ""
            User_Data(name, contact, description)
        }
}