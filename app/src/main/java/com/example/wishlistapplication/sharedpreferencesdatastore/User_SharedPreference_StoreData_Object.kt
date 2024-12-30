package com.example.wishlistapplication.sharedpreferencesdatastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object User_SharedPreference_StoreData_Object {
        val Context.dataStore by preferencesDataStore(name = "user_details")

        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_CONTACT_KEY = stringPreferencesKey("user_contact")
        val USER_DESCRIPTION_KEY = stringPreferencesKey("user_description")
}
