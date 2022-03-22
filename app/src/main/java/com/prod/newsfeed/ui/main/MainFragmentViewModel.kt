package com.prod.newsfeed.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.newsfeed.domain.NewsFeedRepository
import com.prod.newsfeed.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class MainFragmentViewModel @Inject constructor(
	private val repository: NewsFeedRepository,
	private val preferenceManager: PreferenceManager,
) : ViewModel() {
	private val _refreshStatus = MutableLiveData<Boolean>()
	val refreshStatus: LiveData<Boolean>
		get() = _refreshStatus
	val listNews = repository.getNews()

	init {
		viewModelScope.launch {
			val lastUpdateTime = preferenceManager.getDataLastUpdateTime().firstOrNull() ?: 0
			if (System.currentTimeMillis() - lastUpdateTime > 1800000)
				refreshNews()
		}
	}

	fun refreshNews() {
		_refreshStatus.value = true
		viewModelScope.launch(Dispatchers.IO) {
			repository.refreshNewsFeed()
			viewModelScope.launch {
				_refreshStatus.value = false
			}
		}
	}
}