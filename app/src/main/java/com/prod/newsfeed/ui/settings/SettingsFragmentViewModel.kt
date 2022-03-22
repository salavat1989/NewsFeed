package com.prod.newsfeed.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.newsfeed.utils.COUNTRY_CODES
import com.prod.newsfeed.utils.PreferenceManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class SettingsFragmentViewModel @Inject constructor(
	private val preferenceManager: PreferenceManager,
) : ViewModel() {

	private val _radioContent = MutableLiveData<Map<String, Boolean>>()
	val radioContent: LiveData<Map<String, Boolean>>
		get() = _radioContent

	init {
		createRadioMap()
	}

	private fun createRadioMap() {
		viewModelScope.launch {
			val countryCode = preferenceManager.getCountryCode().first()
			val map = mutableMapOf<String, Boolean>()
			for (code in COUNTRY_CODES) map[code.uppercase()] = countryCode == code
			_radioContent.value = map
		}
	}

	fun radioChecked(code: String) {
		viewModelScope.launch {
			preferenceManager.setCountryCode(code.lowercase())
			createRadioMap()
		}
	}
}