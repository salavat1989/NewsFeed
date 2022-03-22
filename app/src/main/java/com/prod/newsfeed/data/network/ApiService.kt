package com.prod.newsfeed.data.network

import com.prod.newsfeed.data.network.model.NewsContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

interface ApiService {
	@GET("top-headlines")
	suspend fun getNews(
		@Query(COUNTRY) country: String,
		@Query(API_KEY) apiKey: String = MY_API_KEY,
		@Query(PAGE_SIZE) pageSize: Int = DEFAULT_PAGE_SIZE,

		): NewsContainerDto

	companion object {
		private const val COUNTRY = "country"
		private const val API_KEY = "apiKey"
		private const val PAGE_SIZE = "pageSize"
		private const val DEFAULT_PAGE_SIZE = 100
		//for test task only
		private const val MY_API_KEY = "93e96be2ca2f4be6bf353bf20241ceca"
	}
}