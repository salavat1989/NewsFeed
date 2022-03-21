package com.prod.newsfeed.data

import android.app.Application
import android.util.Log
import com.prod.newsfeed.data.database.NewsFeedDao
import com.prod.newsfeed.data.mapper.NewsMapper
import com.prod.newsfeed.data.network.ApiService
import com.prod.newsfeed.domain.NewsFeedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsFeedRepositoryImpl @Inject constructor(
	private val apiService: ApiService,
	private val newsFeedDao: NewsFeedDao,
	private val application: Application,
	private val mapper: NewsMapper
) : NewsFeedRepository {

	val country = application.resources.configuration.locales.get(0)
		.country.toString().lowercase()

	private val scope = CoroutineScope(Dispatchers.IO)

	override suspend fun refreshNewsFeed(): Boolean {
		val result = true
		val newsContainerDto = apiService.getNews(country)
		val listNewsDb = mapper.mapNewsContainerDtoToListNewsDb(newsContainerDto)
		newsFeedDao.insertNewsAfterDelete(listNewsDb)
		return result
	}
}