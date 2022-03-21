package com.prod.newsfeed.domain

import androidx.lifecycle.LiveData
import com.prod.newsfeed.domain.model.News

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

interface NewsFeedRepository {
	suspend fun refreshNewsFeed(): Boolean

	fun getNews(): LiveData<List<News>>
}