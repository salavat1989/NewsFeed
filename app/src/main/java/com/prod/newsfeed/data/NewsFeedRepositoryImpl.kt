package com.prod.newsfeed.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingSource
import com.prod.newsfeed.data.database.NewsFeedDao
import com.prod.newsfeed.data.mapper.NewsMapper
import com.prod.newsfeed.data.network.ApiService
import com.prod.newsfeed.data.network.EverythingNewsPagingSource
import com.prod.newsfeed.domain.NewsFeedRepository
import com.prod.newsfeed.domain.model.News
import com.prod.newsfeed.utils.COUNTRY_CODES
import com.prod.newsfeed.utils.DEFAULT_COUNTRY_CODE
import com.prod.newsfeed.utils.PreferenceManager
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

class NewsFeedRepositoryImpl @Inject constructor(
	private val apiService: ApiService,
	private val newsFeedDao: NewsFeedDao,
	private val application: Application,
	private val mapper: NewsMapper,
	private val preferenceManager: PreferenceManager,
	private val everythingNewsPagingSourceFactory: EverythingNewsPagingSource.Factory,
) : NewsFeedRepository {

	override suspend fun refreshTopNewsFeed(): Boolean {
		var result = true
		try {
			val country = getSelectedCountryCode()
			val response = apiService.getNews(country)
			if (response.isSuccessful) {
				val articlesDto = response.body()?.articles ?: emptyList()
				val listNewsDb = mapper.mapListArticleDtoToListNewsDb(articlesDto)
				if (listNewsDb.isNotEmpty()) {
					newsFeedDao.insertNewsAfterDelete(listNewsDb)
					preferenceManager.setDataLastUpdateTime(System.currentTimeMillis())
				}
			}
		} catch (e: Exception) {
			result = false
		}
		return result
	}

	override fun getTopNews(): LiveData<List<News>> {
		val listNewsDB = newsFeedDao.getNews()
		return Transformations.map(listNewsDB) {
			mapper.mapListNewsDbToListNews(it)
		}
	}

	private suspend fun getSelectedCountryCode(): String {
		val savedCountryCode = preferenceManager.getCountryCode().firstOrNull() ?: ""
		return if (savedCountryCode != "") {
			savedCountryCode
		} else {
			val country = application.resources.configuration.locales.get(0)
				.country.toString().lowercase()
			val rightCountry = if (COUNTRY_CODES.contains(country)) {
				country
			} else {
				DEFAULT_COUNTRY_CODE
			}
			preferenceManager.setCountryCode(rightCountry)
			rightCountry
		}
	}

	override fun queryEverything(query: String): PagingSource<Int, News> {
		return everythingNewsPagingSourceFactory.create(query)
	}
}