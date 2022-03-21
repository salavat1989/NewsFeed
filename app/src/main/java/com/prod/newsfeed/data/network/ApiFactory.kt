package com.prod.newsfeed.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

object ApiFactory {
	private const val BASE_URL = "https://newsapi.org/v2/"

	private val retrofit = Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create())
		.baseUrl(BASE_URL)
		.build()

	val apiService =
		retrofit.create(ApiService::class.java) ?: throw RuntimeException("ApiService is null")
}