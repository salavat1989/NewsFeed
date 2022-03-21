package com.prod.newsfeed.ui.main

import androidx.lifecycle.ViewModel
import com.prod.newsfeed.domain.NewsFeedRepository
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class MainFragmentViewModel @Inject constructor(
	private val repository: NewsFeedRepository,
) : ViewModel() {
	val listNews = repository.getNews()
}