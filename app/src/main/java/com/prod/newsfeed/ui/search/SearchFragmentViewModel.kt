package com.prod.newsfeed.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.prod.newsfeed.domain.NewsFeedRepository
import com.prod.newsfeed.domain.model.News
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 22.03.2022
 */

class SearchFragmentViewModel @Inject constructor(
	private val repository: NewsFeedRepository,
) : ViewModel() {
	private val _query = MutableStateFlow("")
	val query: StateFlow<String>
		get() = _query.asStateFlow()

	private var newPagingSource: PagingSource<*, *>? = null
	@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
	val listNews: StateFlow<PagingData<News>> = query
		.map(::newPager)
		.flatMapLatest { pager ->
			pager.flow
		}
		.cachedIn(viewModelScope)
		.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

	fun setQuery(query: String) {
		_query.tryEmit(query)
	}

	private fun newPager(query: String): Pager<Int, News> {
		return Pager(PagingConfig(50, enablePlaceholders = false)) {
			repository.queryEverything(query).also {
				newPagingSource = it
			}
		}
	}

}