package com.prod.newsfeed.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.prod.newsfeed.domain.model.News

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

interface NewsFeedRepository {
	suspend fun refreshTopNewsFeed(): Boolean

	fun getTopNews(): LiveData<List<News>>

	fun queryEverything(query: String): PagingSource<Int, News>
}