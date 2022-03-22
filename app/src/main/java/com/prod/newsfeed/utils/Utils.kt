package com.prod.newsfeed.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_DATA_STORE_NAME)