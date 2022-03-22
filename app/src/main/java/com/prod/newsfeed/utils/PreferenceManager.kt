package com.prod.newsfeed.utils

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class PreferenceManager @Inject constructor(private val application: Application) {
	suspend fun setDataLastUpdateTime(time: Long) {
		application.dataStore.edit {
			it[longPreferencesKey(PREF_LAST_UPDATE_TIME_LONG)] = time
		}
	}

	fun getDataLastUpdateTime(): Flow<Long> {
		return application.dataStore.data.map {
			it[longPreferencesKey(PREF_LAST_UPDATE_TIME_LONG)] ?: 0
		}
	}

	suspend fun setCountryCode(code: String) {
		application.dataStore.edit {
			it[stringPreferencesKey(PREF_COUNTRY_CODE)] = code
		}
	}

	fun getCountryCode(): Flow<String> {
		return application.dataStore.data.map {
			it[stringPreferencesKey(PREF_COUNTRY_CODE)] ?: ""
		}
	}

	companion object {
		const val PREF_LAST_UPDATE_TIME_LONG = "last_update_time"
		const val PREF_COUNTRY_CODE = "country_code"
	}
}