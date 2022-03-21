package com.prod.newsfeed.domain.model

/**
 * Created by Kadyrov Salavat on 21.03.2022
 */

data class News(
	val sourceName: String,
	val title: String,
	val description: String,
	val url: String,
	val urlToImage: String,
	val publishedAt: String,
)
